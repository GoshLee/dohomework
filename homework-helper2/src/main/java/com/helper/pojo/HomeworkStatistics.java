package com.helper.pojo;

/**
 * 作业统计
 */
public class HomeworkStatistics {
    Homework homework;

    Integer sumPerson;

    Integer unFinishPerson;

    Integer finishPerson;

    public HomeworkStatistics(Homework homework, Integer sumPerson, Integer unFinishPerson, Integer finishPerson) {
        this.homework = homework;
        this.sumPerson = sumPerson;
        this.unFinishPerson = unFinishPerson;
        this.finishPerson = finishPerson;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Integer getSumPerson() {
        return sumPerson;
    }

    public void setSumPerson(Integer sumPerson) {
        this.sumPerson = sumPerson;
    }

    public Integer getUnFinishPerson() {
        return unFinishPerson;
    }

    public void setUnFinishPerson(Integer unFinishPerson) {
        this.unFinishPerson = unFinishPerson;
    }

    public Integer getFinishPerson() {
        return finishPerson;
    }

    public void setFinishPerson(Integer finishPerson) {
        this.finishPerson = finishPerson;
    }
}
