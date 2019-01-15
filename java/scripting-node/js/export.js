
function infoLog(content) {
    console.log(`info : ${content}`);
}

function errorLog(content) {
    console.log(`error : ${content}`);
}

module.exports.info = infoLog;
module.exports.error = errorLog;