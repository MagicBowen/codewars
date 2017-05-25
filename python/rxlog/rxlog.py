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
