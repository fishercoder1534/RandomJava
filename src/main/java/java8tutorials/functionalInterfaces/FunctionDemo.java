package java8tutorials.functionalInterfaces;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class FunctionDemo {

    public static void main(String... args) throws ExecutionException, InterruptedException {
        System.out.println("Program started.");
        FunctionDemo main = new FunctionDemo();
        String originalInput = "originalInput";
        String result = main.doWorkInMultipleStepsInSequence(originalInput);
        System.out.println("Program ended, result: " + result);
    }

    String doWorkInMultipleStepsInSequence(String messageOne) throws InterruptedException {
        return doWorkStepTwoAsync(messageOne, doWorkStepTwoFunction);
    }

    String doWorkStepTwoAsync(String message, Function<String, String> doWorkStepTwoFunction) throws InterruptedException {
        Thread.sleep(1000);
        StringBuilder sb = new StringBuilder(message);
        System.out.println("Spent 1 second doing work in Step Two Async function.");
        sb.append(",aboutToCallDoWorkStepTwoFunction");
        String intermediateResult = doWorkStepTwoFunction.apply(sb.toString());
        return doWorkStepThreeAsync(intermediateResult, doWorkStepThreeFunction);
    }

    String doWorkStepThreeAsync(String message, Function<String, String> doWorkStepThreeFunction) throws InterruptedException {
        Thread.sleep(1000);
        StringBuilder sb = new StringBuilder(message);
        System.out.println("Spent 1 second doing work in Step Three Async function.");
        sb.append(",aboutToCallDoWorkStepThreeFunction");
        return doWorkStepThreeFunction.apply(sb.toString());
    }

    Function<String, String> doWorkStepTwoFunction = s -> {
        StringBuilder sb = new StringBuilder(s);
        try {
            Thread.sleep(1000);
            System.out.println("Spent 1 second doing work in Step Two.");
            sb.append(",stepTwoDone");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    };

    Function<String, String> doWorkStepThreeFunction = s -> {
        StringBuilder sb = new StringBuilder(s);
        try {
            Thread.sleep(1000);
            System.out.println("Spent 1 second doing work in Step Three.");
            sb.append(",stepThreeDone");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    };

}
