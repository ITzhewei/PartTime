package com.zzw.john.parttime.bean;

/**
 * Created by john(Zhewei) on 2016/12/4.
 */

public class AddBean {

    /**
     * flag : true
     * statusrecord : {"jobID":1,"requestID":1,"responseID":1,"state":0,"statusID":0}
     */

    private String flag;
    /**
     * jobID : 1
     * requestID : 1
     * responseID : 1
     * state : 0
     * statusID : 0
     */

    private StatusrecordBean statusrecord;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public StatusrecordBean getStatusrecord() {
        return statusrecord;
    }

    public void setStatusrecord(StatusrecordBean statusrecord) {
        this.statusrecord = statusrecord;
    }

    public static class StatusrecordBean {
        private int jobID;
        private int requestID;
        private int responseID;
        private int state;
        private int statusID;

        public int getJobID() {
            return jobID;
        }

        public void setJobID(int jobID) {
            this.jobID = jobID;
        }

        public int getRequestID() {
            return requestID;
        }

        public void setRequestID(int requestID) {
            this.requestID = requestID;
        }

        public int getResponseID() {
            return responseID;
        }

        public void setResponseID(int responseID) {
            this.responseID = responseID;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getStatusID() {
            return statusID;
        }

        public void setStatusID(int statusID) {
            this.statusID = statusID;
        }
    }
}
