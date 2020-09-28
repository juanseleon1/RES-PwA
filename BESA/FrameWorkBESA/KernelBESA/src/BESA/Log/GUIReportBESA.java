/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Log;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author User
 */
public class GUIReportBESA extends ReportBESA {

    /**
     *
     */
    private SimpleAttributeSet traceAttrs;
    /**
     *
     */
    private SimpleAttributeSet debugAttrs;
    /**
     *
     */
    private SimpleAttributeSet infoAttrs;
    /**
     *
     */
    private SimpleAttributeSet warnAttrs;
    /**
     *
     */
    private SimpleAttributeSet errorAttrs;
    /**
     *
     */
    private SimpleAttributeSet fatalAttrs;
    /**
     *
     */
    private BlogClient bgui;

    /**
     * 
     */
    public GUIReportBESA() {
        configureLog();
        bgui = new BlogClient();
    }

    /**
     *
     * @param message
     * @param time
     */
    @Override
    protected void traceImp(Object message, long time) {
        send(traceAttrs, BLevel.TRACE, time, message.toString());
    }

    /**
     *
     * @param message
     * @param time
     */
    @Override
    protected void debugImp(Object message, long time) {
        send(debugAttrs, BLevel.DEBUG, time, message.toString());
    }

    /**
     *
     * @param message
     * @param time
     */
    @Override
    protected void infoImp(Object message, long time) {
        send(infoAttrs, BLevel.INFO, time, message.toString());

    }

    /**
     *
     * @param message
     * @param time
     */
    @Override
    protected void warnImp(Object message, long time) {
        send(warnAttrs, BLevel.WARN, time, message.toString());
    }

    /**
     *
     * @param message
     * @param time
     */
    @Override
    protected void errorImp(Object message, long time) {
        send(errorAttrs, BLevel.ERROR, time, message.toString());
    }

    /**
     *
     * @param message
     * @param time
     */
    @Override
    protected void fatalImp(Object message, long time) {
        send(fatalAttrs, BLevel.FATAL, time, message.toString());
    }

    /**
     *
     * @param time
     * @return
     */
    private void send(SimpleAttributeSet attrs, BLevel level, long time, String data) {
        String rtn = "";
        Message message = new Message();

        message.setAttrs(attrs);


        if (level != BLevel.INFO && level != BLevel.WARN) {
            rtn += "[" + level.name() + "]:    ";
        } else {
            rtn += "[" + level.name() + "]:     ";
        }
        message.setLevel(level);

        //rtn += "" + time + "\t";
        message.setTime(time);

        //--------------------------------------------------------------------//
        // Gets the time.                                                     //
        //--------------------------------------------------------------------//

        //--------------------------------------------------------------------//
        // Gets the time.                                                     //
        //--------------------------------------------------------------------//
        Thread t = Thread.currentThread();
        StackTraceElement stack[] = (new Throwable()).getStackTrace();

        //rtn += t.getName() + "\t";
        message.setThread(t.getName());

        //rtn += getName(stack[2].getClassName()) + "\t";
        message.setClassName(stack[2].getClassName());

        //rtn += stack[2].getMethodName() + "\t";
        message.setMethodName(stack[2].getMethodName());

        //rtn += stack[2].getLineNumber() + "\t";
        message.setLineNumber(String.valueOf(stack[2].getLineNumber()));

        rtn += data;
        message.setData(data);

        System.out.println(rtn);

        if (bgui.send(message)) {
        }
    }

    /**
     *
     * @return
     */
    public BlogClient getBgui() {
        return bgui;
    }

    /**
     *
     */
    protected void configureLog() {
        //--------------------------------------------------------------------//
        // Trace.
        //--------------------------------------------------------------------//
        traceAttrs = new SimpleAttributeSet();
        StyleConstants.setForeground(traceAttrs, Color.MAGENTA);
        //--------------------------------------------------------------------//
        //
        //--------------------------------------------------------------------//
        debugAttrs = new SimpleAttributeSet();
        StyleConstants.setForeground(debugAttrs, Color.GREEN);
        //--------------------------------------------------------------------//
        //
        //--------------------------------------------------------------------//
        infoAttrs = new SimpleAttributeSet();
        //--------------------------------------------------------------------//
        //
        //--------------------------------------------------------------------//
        warnAttrs = new SimpleAttributeSet();
        StyleConstants.setForeground(warnAttrs, Color.YELLOW);
        //--------------------------------------------------------------------//
        //
        //--------------------------------------------------------------------//
        errorAttrs = new SimpleAttributeSet();
        StyleConstants.setForeground(errorAttrs, Color.ORANGE);
        //--------------------------------------------------------------------//
        //
        //--------------------------------------------------------------------//
        fatalAttrs = new SimpleAttributeSet();
        StyleConstants.setForeground(fatalAttrs, Color.RED);
        //--------------------------------------------------------------------//
        //
        //--------------------------------------------------------------------//
    }
}
