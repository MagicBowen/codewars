function doSomething() {
    console.log(`the name of this is ${this.name}`);
    this.method();
}

module.exports.doSomething = doSomething;