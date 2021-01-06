const http = require('http');
const url = require('url');
const fs = require('fs');
http.createServer((req, res) => {
    const path = url.parse(req.url, true).pathname;
    if(req.method === 'GET'){
        if(path  === '/'){
            res.writeHead(200, {'Content-Type':'text/html'});
            fs.readFile(__dirname+'/mobilenet.html', (err, data) => {
                if(err)
                    return console.error(err);

                res.end(data,'utf-8');
            });
        }
    }
}).listen(5000);