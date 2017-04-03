package org.oopdev.xbalancer.common.exception;


/**
 *
 */
public class XbCommonException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param code {@link ErrorCode}  the detail message. The detail message is saved for
     *             later retrieval by the {@link #getCode()} ()} method.
     */
    public XbCommonException(int code) {
        super();
        this.code = code;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param error {@link ErrorCode}  the detail message. The detail message is saved for
     *              later retrieval by the {@link #getCode()} ()} method.
     */
    public XbCommonException(ErrorCode error) {
        super(error.getStatus().getMessage());
        this.code = error.getStatus().getCode();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param code    {@link ErrorCode}  the detail message. The detail message is saved for
     *                later retrieval by the {@link #getCode()} ()} method.
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public XbCommonException(ErrorCode error, String message) {
        super(message);
        this.code = error.getStatus().getCode();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public XbCommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public XbCommonException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public XbCommonException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
