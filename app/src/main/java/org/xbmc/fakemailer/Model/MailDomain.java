package org.xbmc.fakemailer.Model;import com.google.gson.annotations.Expose;import com.google.gson.annotations.SerializedName;import java.io.Serializable;import java.util.List;publicclass MailDomain implements Serializable{        @SerializedName("success")        @Expose        private boolean success;        @SerializedName("message")        @Expose        private String message;        @SerializedName("result")        @Expose        private List<String> result = null;        private final static long serialVersionUID = -6904074214694597168L;        public boolean isSuccess() {            return success;        }        public void setSuccess(boolean success) {            this.success = success;        }        public String getMessage() {            return message;        }        public void setMessage(String message) {            this.message = message;        }        public List<String> getResult() {            return result;        }        public void setResult(List<String> result) {            this.result = result;        }    }