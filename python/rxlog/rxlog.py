import csv
from rx import Observable

WINDOW = 1

def generate_csv_stream(file_path):
    with open(file_path) as file:
        csv_reader = csv.reader(file)
        for row in csv_reader:
            items = [x.strip() for x in row if x.strip()]
            yield items

class Aggregator:
    def __init__(self, type, time, anchor_time):
        self.type = type
        self.time = time  
        self.anchor_time = anchor_time        

def aggregate(acc, x):
    return Aggregator(x['type'], x['time'], x['time']) if (x['time'] - acc.time) > WINDOW else Aggregator(None, 0, acc.anchor_time)
    

stream = Observable.from_(generate_csv_stream('issue.csv'))                       \
                   .map(lambda items: {'type': items[1], 'time': int(items[0])})     \
                   .scan(aggregate, Aggregator(None, 0, 0))                  \
                   .filter(lambda item: item.type is not None)              \
                   .map(lambda item: {'type' : item.type, 'time' : item.time})                                    \
                   .subscribe(lambda item: print('receive {}'.format(item)))    



class StreamWrapper:
  def __init__(self, value, sid = 0):
    self.value = value
    self.property = {'id' : sid}

  def set(self, name, value):
    self.property[name] = value

  def get(self, name):
    return self.property[name]

  def value(self):
    return self.value

  def __str__(self):
    return 'value: {}, property: {}'.format(self.value, self.property)

# bucket = {'s' : [[],[]], 'value' : None}    

def order(acc, item):
  acc['s'][item['id']].append(item['value'])

  if len(acc['s'][0]) == 0 or len(acc['s'][1]) == 0:
    return {'s' : acc['s'], 'value' : None}

  result = []

  while len(acc['s'][0]) != 0 and len(acc['s'][1]) != 0:
    if acc['s'][0][0] < acc['s'][1][0]:
      result.append(acc['s'][0][0])
      acc['s'][0].pop(0)
    else:
      result.append(acc['s'][1][0])
      acc['s'][1].pop(0)  

  return  {'s' : acc['s'], 'value' : Observable.from_(result)}



s1 = Observable.from_([1, 3, 4, 6, 9, 10, 11, 13, 14]).map(lambda item: {'id' : 0, 'value' : item})
s2 = Observable.from_([0, 2, 5, 7, 8, 12, 15]).map(lambda item: {'id' : 1, 'value' : item})

s1.merge(s2).scan(order, {'s' : [[],[]], 'value' : None}).filter(lambda item: item['value'] is not None).flat_map(lambda item: item['value']).subscribe(lambda item: print(item))



