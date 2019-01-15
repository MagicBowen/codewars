import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

public class JavaCallbackImpl implements JavaCallback {
    protected Global g;

    JavaCallbackImpl(Global g) {
        this.g = g;
    }

    public Object invoke(V8Object v8Object, V8Array v8Array) {
        return this.g;
    }
}
