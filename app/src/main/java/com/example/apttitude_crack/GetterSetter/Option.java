package com.example.apttitude_crack.GetterSetter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Option {

        @SerializedName("objid")
        @Expose
        private String objid;
        @SerializedName("options")
        @Expose
        private String options;

        public Option(String objid,String options)
        {
            this.objid = objid;
            this.options = options;
        }
        public String getObjid() {
            return objid;
        }

        public void setObjid(String objid) {
            this.objid = objid;
        }

        public String getOptions() {
            return options;
        }

        public void setOptions(String options) {
            this.options = options;
        }

    }

