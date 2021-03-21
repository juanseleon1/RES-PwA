/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class TerminalReportBESA extends ReportBESA {

    /**
     *
     */
    private boolean trace;
    /**
     *
     */
    private boolean debug;
    /**
     *
     */
    private boolean info;
    /**
     *
     */
    private boolean warn;
    /**
     *
     */
    private boolean error;
    /**
     *
     */
    private boolean fatal;
    /**
     *
     */
    private String dateFormat = "";
    Date date;
    SimpleDateFormat simpleDateFormat;

    /**
     *
     */
    public TerminalReportBESA(String path) {
        super(path);
        this.trace = configLog.isTrace();
        this.debug = configLog.isDebug();
        this.info = configLog.isInfo();
        this.warn = configLog.isWarn();
        this.error = configLog.isError();
        this.fatal = configLog.isFatal();
        this.dateFormat = configLog.getDateFormat();
        date = new Date();
        simpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    /**
     *
     */
    public TerminalReportBESA() {
        super();
        this.trace = configLog.isTrace();
        this.debug = configLog.isDebug();
        this.info = configLog.isInfo();
        this.warn = configLog.isWarn();
        this.error = configLog.isError();
        this.fatal = configLog.isFatal();
        this.dateFormat = configLog.getDateFormat();
        date = new Date();
        simpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    @Override
    protected void traceImp(Object message, long time) {
        if (trace) {
            display(BLevel.TRACE, time, message.toString());
        }
    }

    @Override
    protected void debugImp(Object message, long time) {
        if (debug) {
            display(BLevel.DEBUG, time, message.toString());
        }
    }

    @Override
    protected void infoImp(Object message, long time) {
        if (info) {
            display(BLevel.INFO, time, message.toString());
        }
    }

    @Override
    protected void warnImp(Object message, long time) {
        if (warn) {
            display(BLevel.WARN, time, message.toString());
        }
    }

    @Override
    protected void errorImp(Object message, long time) {
        if (error) {
            if (message != null) {
                display(BLevel.ERROR, time, message.toString());
            } else {
                display(BLevel.ERROR, time, "No message.");
            }
        }
    }

    @Override
    protected void fatalImp(Object message, long time) {
        if (fatal) {
            display(BLevel.FATAL, time, message.toString());
        }
    }

    private void display(BLevel level, long time, String data) {
        Thread t = Thread.currentThread();
        StackTraceElement stack[] = (new Throwable()).getStackTrace();

        System.out.printf("%-70s%-30s%n",
                "[" + level.name() + "]:"
                + "[" + paserTime(time) + "]:"
                + "[" + getName(stack[3].getClassName()) + ":" + stack[3].getMethodName() + ":" + stack[3].getLineNumber() + "]:", data);
    }

    /**
     *
     * @param className
     * @return
     */
    private String getName(String className) {
        StringBuilder buffer = new StringBuilder();
        for (int index = className.length() - 1; index >= 0; index--) {
            if (className.charAt(index) != '.') {
                buffer.append(className.charAt(index));
            } else {
                return buffer.reverse().toString();
            }
        }
        return className;
    }

    private String paserTime(long time) {
        date.setTime(time);
        return simpleDateFormat.format(date);
    }
}
