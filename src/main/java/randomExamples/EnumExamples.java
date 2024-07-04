package randomExamples;

public class EnumExamples {
    public static void main(String... args) {
        System.out.println(RequestType.GET);
        System.out.println(RequestType.GET == RequestType.GET);
        System.out.println(RequestType.GET.name());
    }
}

enum RequestType {
    GET,
    PUT,
    POST,
    PATCH,
    DELETE
}
