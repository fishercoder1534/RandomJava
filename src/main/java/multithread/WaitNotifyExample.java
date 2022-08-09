package multithread;

public class WaitNotifyExample {
    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    public synchronized String receive() {
        System.out.println("In receive method now, transfer: " + transfer);
        while (transfer) {
            try {
                System.out.println("waiting to receive now.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread Interrupted");
            }
        }
        transfer = true;

        String returnPacket = packet;
        System.out.println("In receive method, notifyAll now.");
        notifyAll();
        System.out.println("In receive method now, returnPacket: " + returnPacket);
        return returnPacket;
    }

    public synchronized void send(String packet) {
        System.out.println("In send method now, transfer: " + transfer);
        while (!transfer) {
            try {
                System.out.println("waiting to send now.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread Interrupted");
            }
        }
        transfer = false;

        this.packet = packet;
        System.out.println("In send method, notifyAll now.");
        notifyAll();
        System.out.println("In send method now, packet: " + packet);
    }

    public static void main(String... args) {
        System.out.println("Hello world...");
        WaitNotifyExample waitNotifyExample = new WaitNotifyExample();
        waitNotifyExample.send("This is a nice test packet..");
        waitNotifyExample.receive();
        System.out.println("Program finished...");
    }
}
