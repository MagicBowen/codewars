// load module 'http' (this is blocking) to handle http requests
var http = require('http');

// when there is a request we return 'Hello, World\n'

// function handleRequest(req, res) {
//   res.writeHead(200, {'Content-Type': 'text/plain'});
//   res.end('Hello, World\n');
// }

// we listen on localhost, port 1337
// and give handleRequest as call back
// you see the non-blocking / asynchronous nature here

// http.createServer(handleRequest).listen(1337, '127.0.0.1');

// logs to the console to reassure that we are on our way

// console.log('Get your hello at http://127.0.0.1:1337/');

/*********************************************************/

function localAdd(a, b) {
    return a + b
}

function remoteAdd(a, b) {
    var url = "http://localhost:5000/add?a=" + a + "&b=" + b
    var json = "";
    http.get(url, function(res) {
        console.log("Got response: " + res.statusCode);
        res.on('data', function (d) {
            json += d;
        });
        res.on('end',function(){
            //获取到的数据
            json = JSON.parse(json);
            console.log(json)
        });
    }).on('error', function(e) {
        console.log("Got error: " + e.message);
    });
    return json.result;
}

var argv = process.argv.splice(2)
remoteAdd(argv[0], argv[1])
//remoteAdd(3,4)