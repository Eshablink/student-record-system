package com.example.studentrecords;

/**
 * Represents a postgraduate student with an additional research topic.
 * Demonstrates inheritance and polymorphism.
 */
public class PostgraduateStudent extends Student {
    private String researchTopic;

    /**
     * Creates a postgraduate student with the provided values.
     *
     * @param id unique student identifier
     * @param name student name
     * @param age student age
     * @param course enrolled course name
     * @param cgpa current CGPA value
     * @param researchTopic research specialization topic
     */
    public PostgraduateStudent(int id, String name, int age, String course, double cgpa, String researchTopic) {
        super(id, name, age, course, cgpa);
        this.researchTopic = researchTopic;
    }

    /**
     * Gets the research topic.
     *
     * @return research topic
     */
    public String getResearchTopic() {
        return researchTopic;
    }

    /**
     * Sets the research topic.
     *
     * @param researchTopic research topic
     */
    public void setResearchTopic(String researchTopic) {
        this.researchTopic = researchTopic;
    }

    /**
     * Returns a specialized display string for postgraduate students.
     *
     * @return formatted postgraduate student details
     */
    @Override
    public String display() {
        return String.format(
                "ID: %d | Name: %s | Age: %d | Course: %s | CGPA: %.2f | Type: Postgraduate | Research Topic: %s",
                getId(),
                getName(),
                getAge(),
                getCourse(),
                getCgpa(),
                researchTopic
        );
    }

    /**
     * Converts the postgraduate student object into a text line for file storage.
     *
     * @return serialized postgraduate record
     */
    @Override
    public String toFileString() {
        return String.join("|",
                "PostgraduateStudent",
                String.valueOf(getId()),
                getName(),
                String.valueOf(getAge()),
                getCourse(),
                String.valueOf(getCgpa()),
                researchTopic);
    }
}
