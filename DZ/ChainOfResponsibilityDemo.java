
abstract class Handler {                  // определяет интерфейс и ссылку на след обработчик
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(Request request);
}


class ConcreteHandler1 extends Handler { // Конкретные обработчики опр логику
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE1) {
            System.out.println("Handler1 обрабатывает запрос: " + request.getMessage());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class ConcreteHandler2 extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE2) {
            System.out.println("Handler2 обрабатывает запрос: " + request.getMessage());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class ConcreteHandler3 extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE3) {
            System.out.println("Handler3 обрабатывает запрос: " + request.getMessage());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}


class Request { // Класс запроса содержит инфу о типе
    private RequestType type;
    private String message;

    public Request(RequestType type, String message) {
        this.type = type;
        this.message = message;
    }

    public RequestType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}


enum RequestType {
    TYPE1, TYPE2, TYPE3
}

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();

        // цепочкa
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);


        Request request1 = new Request(RequestType.TYPE1, "Запрос типа 1");
        Request request2 = new Request(RequestType.TYPE2, "Запрос типа 2");
        Request request3 = new Request(RequestType.TYPE3, "Запрос типа 3");
        Request request4 = new Request(RequestType.TYPE1, "Еще один запрос типа 1");

        System.out.println(" Обработка запросов через цепочку обязанностей ");
        handler1.handleRequest(request1);
        handler1.handleRequest(request2);
        handler1.handleRequest(request3);
        handler1.handleRequest(request4);
    }
}