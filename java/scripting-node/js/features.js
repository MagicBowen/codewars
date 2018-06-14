console.log(process.version);

const a = 1;
const b = '23';

console.log('support const!');

const add = (a, b) => {
    let c = a + b;
    return c;
}

console.log(`result of ${a} add ${b} is ${add(a,b)}`);

console.log('support block function!');


class Foo {
    constructor(a, b) {
        this.lhs = a;
        this.rhs = b;
    }

    get result() {
        return this.lhs + this.rhs;
    }
}

console.log('support class!');

let foo = new Foo('45', 6);

console.log(`result of foo is ${foo.result}`);

console.log('support let!');

async function remoteAdd(a, b) {
    return a + b;
}

(async () => {
    let result = await remoteAdd('7', 89);
    console.log(`result of async add is ${result}`);
})();

console.log('support async & await!');

