var http = require('http');

function localAdd(a, b) {
    return a + b
}

function remoteAdd(a, b, callback) {
    var url = "http://localhost:5000/add?a=" + a + "&b=" + b
    var json = "";
    http.get(url, function(res) {
        console.log("Get response: " + res.statusCode);
        res.on('data', function (d) {
            json += d;
        });
        res.on('end',function(){
            result = JSON.parse(json);
            console.log(`http result = ${JSON.stringify(result)}`);
            callback(result)
        });
    }).on('error', function(e) {
        console.log("Get error: " + e.message);
    });
}

module.exports.add = remoteAdd;