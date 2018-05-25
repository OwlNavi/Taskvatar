package com.example.cwagt.taskapp345.object;

public class Task {
	private String name, description, time;

	private Enums.Frequency frequency;
	private Boolean reminder;
	private Enums.Status status;
	private int priority;

    public Task(String name, String description, String time) {
        this.name = name;
        this.description = description;
        this.time = time;

        //default values
        this.frequency = Enums.Frequency.DAILY;
        this.reminder = false;
        this.status = Enums.Status.INCOMPLETE;
        this.priority = 1;
    }

    public Task(String name, String description, String time, Enums.Frequency frequency, Boolean reminder, Enums.Status status, int priority) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.frequency = frequency;
        this.reminder = reminder;
        this.status = status;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + "', " +
                "description='" + description + "', " +
                "time='" + time + "', " +
                "frequency=" + frequency + ", " +
                "reminder=" + reminder + ", " +
                "status=" + status + ", " +
                "priority=" + priority +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Enums.Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Enums.Frequency frequency) {
        this.frequency = frequency;
    }

    public Boolean getReminder() {
        return reminder;
    }

    public void setReminder(Boolean reminder) {
        this.reminder = reminder;
    }

    public Enums.Status getStatus() {
        return status;
    }

    public void setStatus(Enums.Status status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
