package org.oopdev.xbalancer.common.exception;

/**
 * Created by kamilbukum on 13/03/2017.
 */
public enum ErrorCode {

    HTTP_100(new Status(100, "Continue")),
    HTTP_101(new Status(101, "Switching Protocols")),
    HTTP_102(new Status(102, "Processing")),
    HTTP_200(new Status(200, "OK")),
    HTTP_201(new Status(201, "Created")),
    HTTP_202(new Status(202, "Accepted")),
    HTTP_203(new Status(203, "Non-Authoritative Information")),
    HTTP_204(new Status(204, "No Content")),
    HTTP_205(new Status(205, "Reset Content")),
    HTTP_206(new Status(206, "Partial Content")),
    HTTP_207(new Status(207, "Multi-Status")),
    HTTP_210(new Status(210, "Content Different")),
    HTTP_300(new Status(300, "Multiple Choices")),
    HTTP_301(new Status(301, "Moved Permanently")),
    HTTP_302(new Status(302, "Moved Temporarily")),
    HTTP_303(new Status(303, "See Other")),
    HTTP_304(new Status(304, "Not Modified")),
    HTTP_307(new Status(307, "Temporary Redirect")),
    HTTP_305(new Status(305, "Use Proxy")),
    HTTP_400(new Status(400, "Bad Request")),
    HTTP_401(new Status(401, "Unauthorized")),
    HTTP_402(new Status(402, "Payment Required")),
    HTTP_403(new Status(403, "Forbidden")),
    HTTP_404(new Status(404, "Not Found")),
    HTTP_405(new Status(405, "Not allowed method")),
    HTTP_406(new Status(406, "Not Acceptable")),
    HTTP_407(new Status(407, "Unauthorized on proxy")),
    HTTP_408(new Status(408, "Request timeout")),
    HTTP_409(new Status(409, "Conflicts connections")),
    HTTP_410(new Status(410, "Gone")),
    HTTP_411(new Status(411, "Length Required")),
    HTTP_412(new Status(412, "Precondition Failed")),
    HTTP_413(new Status(413, "Request Entity Too Large")),
    HTTP_414(new Status(414, "Request-URI Too Long")),
    HTTP_415(new Status(415, "Unsupported Media Type")),
    HTTP_416(new Status(416, "Requested range unsatifiable")),
    HTTP_417(new Status(417, "Expectation failed")),
    HTTP_422(new Status(422, "Unprocessable entity")),
    HTTP_423(new Status(423, "Locked")),
    HTTP_424(new Status(424, "Method failure")),
    HTTP_500(new Status(500, "Internal Server Error")),
    HTTP_501(new Status(501, "Not Implemented")),
    HTTP_502(new Status(502, "Invalid gateway")),
    HTTP_503(new Status(503, "Service unavaliable")),
    HTTP_504(new Status(504, "Gateway Timeout")),
    HTTP_505(new Status(505, "HTTP Version not supported")),
    HTTP_507(new Status(507, "Insufficient storage")),
    HTTP_508(new Status(508, "Wrong URL ")),
    HTTP_520(new Status(520, "Requested JSON parse failed.")),
    HTTP_521(new Status(521, "Time out error.")),
    HTTP_523(new Status(523, "Ajax request aborted."));

    private Status status;

    ErrorCode(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
