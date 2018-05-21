package com.example.cwagt.taskapp345.object;

/**
 * Created by cwagt on 30/04/2018.
 */

public class TaskOld {
    private String taskName;
    private String taskDescription;
    private Enums.Frequency taskFrequency;
    private Enums.Status taskStatus;
    private int taskPriority;
    private boolean taskReminder;
    private Enums.Frequency taskReminderFrequency;

    public TaskOld() {
        this.taskName = "Default";
        this.taskDescription = "Default task";
        this.taskFrequency = Enums.Frequency.DAILY;
        this.taskPriority = 0;
        this.taskReminder = true;
        this.taskReminderFrequency = Enums.Frequency.DAILY;
        this.taskStatus = Enums.Status.INCOMPLETE;
    }

    public TaskOld(String name,
                   String taskDescription,
                   Enums.Frequency taskFrequency,
                   Enums.Status taskStatus,
                   int taskPriority,
                   boolean taskReminder,
                   Enums.Frequency taskReminderFrequency) {
        this.taskName = name;
        this.taskDescription = taskDescription;
        this.taskFrequency = taskFrequency;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.taskReminder = taskReminder;
        this.taskReminderFrequency = taskReminderFrequency;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Enums.Frequency getTaskFrequency() {
        return taskFrequency;
    }

    public void setTaskFrequency(Enums.Frequency taskFrequency) {
        this.taskFrequency = taskFrequency;
    }

    public Enums.Status getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Enums.Status taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public boolean isTaskReminder() {
        return taskReminder;
    }

    public void setTaskReminder(boolean taskReminder) {
        this.taskReminder = taskReminder;
    }

    public Enums.Frequency getTaskReminderFrequency() {
        return taskReminderFrequency;
    }

    public void setTaskReminderFrequency(Enums.Frequency taskReminderFrequency) {
        this.taskReminderFrequency = taskReminderFrequency;
    }

    @Override
    public String toString() {
        return "TaskOld{" +
                "taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskFrequency=" + taskFrequency +
                ", taskStatus=" + taskStatus +
                ", taskPriority=" + taskPriority +
                ", taskReminder=" + taskReminder +
                ", taskReminderFrequency=" + taskReminderFrequency +
                '}';
    }
}
