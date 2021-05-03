package HomeSystem;

abstract class Appliance {
    protected String name;
    protected Boolean status;   // if appliance is on or off

    Appliance(String name) {
        this.name = name;
        this.status = false;    // appliances begin turned off
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Boolean getStatus() {
        return status;
    }

    final void turnOn() {
        if (!status) {
            status = true;
            System.out.println(name + ": has been turned on");
        }
    }

    final void turnOff() {
        if (status) {
            status = false;
            System.out.println(name + ": has been turned off");
        }
    }
}
