from flask import Flask, request, abort
from flask_restful import Api, Resource

app = Flask(__name__)
api = Api(app)

class Add(Resource):
    def get(self):
        a = int(request.args.get('a'))
        b = int(request.args.get('b'))
        return {'result' : a + b}


api.add_resource(Add, '/add', endpoint = 'add')


if __name__ == '__main__':
    app.run(debug=True)
