/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Log;

import java.io.Serializable;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author User
 */
public class Message implements Serializable {

    private SimpleAttributeSet attrs;
    private BLevel level;
    private long time;
    private String thread;
    private String className;
    private String methodName;
    private String lineNumber;
    private String data;

    public Message(SimpleAttributeSet attrs, BLevel level, long time, String thread, String className, String methodName, String lineNumber, String data) {
        this.attrs = attrs;
        this.level = level;
        this.time = time;
        this.thread = thread;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.data = data;
    }

    public Message() {
    }

    public SimpleAttributeSet getAttrs() {
        return attrs;
    }

    public void setAttrs(SimpleAttributeSet attrs) {
        this.attrs = attrs;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BLevel getLevel() {
        return level;
    }

    public void setLevel(BLevel level) {
        this.level = level;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
