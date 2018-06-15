/**
 * Created by wangbo on 2017/10/30.
 */

import java.io.File;
import com.eclipsesource.v8.*;

public class Main {
     private static void invokeJsFile(String filename) {
         final NodeJS nodeJS = NodeJS.createNodeJS();

         File nodeScript = new File("./js/" + filename + ".js");

         nodeJS.exec(nodeScript);

         while(nodeJS.isRunning()) {
             nodeJS.handleMessage();
         }
         nodeJS.release();
     }

    private static void testNodeJsModuleExport() {
        final NodeJS nodeJS = NodeJS.createNodeJS();
        final V8Object logger = nodeJS.require(new File("./js/export.js"));

        logger.executeJSFunction("info", "something happened!");
        logger.executeJSFunction("error", "something bad happened!");

        while(nodeJS.isRunning()) {
            nodeJS.handleMessage();
        }

        logger.release();
        nodeJS.release();
    }

    private static void testHttpModuleCallback() {
        final NodeJS nodeJS = NodeJS.createNodeJS();
        final V8Object http = nodeJS.require(new File("./js/http.js"));

        JavaCallback result = new JavaCallback() {

            public Object invoke(V8Object receiver, V8Array parameters) {
                System.out.println("parameter length is " + parameters.length());
                V8Object resultObj = parameters.getObject(0);
                int result = resultObj.getInteger("result");
                System.out.println("result is " + result);
                resultObj.release();
                return null;
            }
        };

        V8Object callback = new V8Function(nodeJS.getRuntime(), result);

        http.executeJSFunction("add", 1, 2, callback);

        while(nodeJS.isRunning()) {
            nodeJS.handleMessage();
        }

        callback.release();
        http.release();
        nodeJS.release();
    }

    private static void testRequestModuleCallback() {
        final NodeJS nodeJS = NodeJS.createNodeJS();
        final V8Object http = nodeJS.require(new File("./js/node-module/index.js"));

        JavaCallback result = new JavaCallback() {

            public Object invoke(V8Object receiver, V8Array parameters) {
                System.out.println("parameter length is " + parameters.length());
                V8Object resultObj = parameters.getObject(0);
                int result = resultObj.getInteger("result");
                System.out.println("result is " + result);
                resultObj.release();
                return null;
            }
        };

        V8Object callback = new V8Function(nodeJS.getRuntime(), result);

        http.executeJSFunction("add", 1, 2, callback);

        while(nodeJS.isRunning()) {
            nodeJS.handleMessage();
        }

        callback.release();
        http.release();
        nodeJS.release();
    }

    private static void testAddFunction() {
        final NodeJS nodeJS = NodeJS.createNodeJS();
        final V8Object doSomething = nodeJS.require(new File("./js/java-callback.js"));

        JavaCallback callback = new JavaCallback() {

            public Object invoke(V8Object receiver, V8Array parameters) {
                return "5";
            }
        };

        nodeJS.getRuntime().registerJavaMethod(callback, "javaFunction");

        doSomething.executeJSFunction("doSomething");

        while(nodeJS.isRunning()) {
            nodeJS.handleMessage();
        }

        doSomething.release();
        nodeJS.release();
    }

    private static void testAddGlobalObject() {
        final NodeJS nodeJS = NodeJS.createNodeJS();

        Global g = new Global(5);

        JavaCallback getValue = new JavaCallbackImpl(g) {

            public Object invoke(V8Object receiver, V8Array parameters) {
                return g.getValue();
            }
        };

        JavaCallback increase = new JavaCallbackImpl(g) {

            public Object invoke(V8Object receiver, V8Array parameters) {
                g.increase();
                return null;
            }
        };

        nodeJS.getRuntime().registerJavaMethod(getValue, "getValueInJava");
        nodeJS.getRuntime().registerJavaMethod(increase, "increaseInJava");


        File nodeScript = new File("./js/" + "use-global.js");

        nodeJS.exec(nodeScript);

        while(nodeJS.isRunning()) {
            nodeJS.handleMessage();
        }

        nodeJS.release();
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        invokeJsFile("features");
        invokeJsFile("require");
        testNodeJsModuleExport();
        testHttpModuleCallback();
        testRequestModuleCallback();
        testAddFunction();
        testAddGlobalObject();
    }
}
