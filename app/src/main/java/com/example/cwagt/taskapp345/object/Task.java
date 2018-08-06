package com.example.cwagt.taskapp345.object;

/**
 * Class representation of a Task
 */
public class Task {
    private Long id;
    //Tasks have a name, description of what is to be done and at what time
	private String name, description, time;
    //How often this task should repeat
	private Enums.Frequency frequency;
	//Should the user be reminded about this task when it is upcoming
	private Boolean reminder;
	//Either completed, in progress or incomplete
	private Enums.Status status;
	//how important is this task
	private int priority;


    /**
     * Convenience constucter sets name, description and time
     * @param name the name of the task
     * @param description description of the task
     * @param time the time the task should occur at
     */
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

    /**
     * Full constructor
     */
    public Task(String name, String description, String time, Enums.Frequency frequency, Boolean reminder, Enums.Status status, int priority) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.frequency = frequency;
        this.reminder = reminder;
        this.status = status;
        this.priority = priority;
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Enums.Frequency getFrequency() {
        return frequency;
    }

    public Boolean getReminder() {
        return reminder;
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

}
