const request = require('request');

function remoteAdd(a, b, callback) {
    var url = "http://localhost:5000/add?a=" + a + "&b=" + b;
    request(url, function (error, response, body) {
      console.log('error:', error);
      console.log('statusCode:', response && response.statusCode);
      console.log('body:', body);
      callback(JSON.parse(body));
    });
}

module.exports.add = remoteAdd;