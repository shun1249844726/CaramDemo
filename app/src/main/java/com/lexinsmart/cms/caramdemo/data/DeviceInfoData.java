package com.lexinsmart.cms.caramdemo.data;

import java.util.List;

/**
 * Created by xushun on 2017/7/11.
 */

public class DeviceInfoData {
    /**
     * result : {"page":{"total":1,"page":0,"size":20},"data":[{"deviceSerial":"695967184","deviceName":"DS-2CD2810FWD(695967184)","deviceType":"DS-2CD2810FWD","category":"UNKNOWN","addTime":1499654826000,"status":1,"isEncrypt":1,"defence":0,"alarmSoundMode":0,"offlineNotify":0,"supportExtShort":"1|1|1|1|0|0|0|0|1|0|0|-1|0|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|0|-1|-1|-1|1|1|1|1|1|1|1|1|1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|","deviceVersion":"V5.4.0 build 160401","deviceCover":"https://i.ys7.com/image/IPC/1.jpeg","cameraNum":1,"detectorNum":0,"cameraInfo":[{"deviceSerial":"695967184","cameraNo":1,"cameraName":"DS-2CD2810FWD(695967184)","isShared":"0","cameraCover":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","videoLevel":2,"videoQualityInfos":[{"videoQualityName":"流畅","videoLevel":0,"streamType":2},{"videoQualityName":"均衡","videoLevel":1,"streamType":2},{"videoQualityName":"高清","videoLevel":2,"streamType":1}]}],"detectorInfo":null}],"code":"200","msg":"操作成功!"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * page : {"total":1,"page":0,"size":20}
         * data : [{"deviceSerial":"695967184","deviceName":"DS-2CD2810FWD(695967184)","deviceType":"DS-2CD2810FWD","category":"UNKNOWN","addTime":1499654826000,"status":1,"isEncrypt":1,"defence":0,"alarmSoundMode":0,"offlineNotify":0,"supportExtShort":"1|1|1|1|0|0|0|0|1|0|0|-1|0|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|0|-1|-1|-1|1|1|1|1|1|1|1|1|1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|","deviceVersion":"V5.4.0 build 160401","deviceCover":"https://i.ys7.com/image/IPC/1.jpeg","cameraNum":1,"detectorNum":0,"cameraInfo":[{"deviceSerial":"695967184","cameraNo":1,"cameraName":"DS-2CD2810FWD(695967184)","isShared":"0","cameraCover":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","videoLevel":2,"videoQualityInfos":[{"videoQualityName":"流畅","videoLevel":0,"streamType":2},{"videoQualityName":"均衡","videoLevel":1,"streamType":2},{"videoQualityName":"高清","videoLevel":2,"streamType":1}]}],"detectorInfo":null}]
         * code : 200
         * msg : 操作成功!
         */

        private PageBean page;
        private String code;
        private String msg;
        private List<DataBean> data;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class PageBean {
            /**
             * total : 1
             * page : 0
             * size : 20
             */

            private int total;
            private int page;
            private int size;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }

        public static class DataBean {
            /**
             * deviceSerial : 695967184
             * deviceName : DS-2CD2810FWD(695967184)
             * deviceType : DS-2CD2810FWD
             * category : UNKNOWN
             * addTime : 1499654826000
             * status : 1
             * isEncrypt : 1
             * defence : 0
             * alarmSoundMode : 0
             * offlineNotify : 0
             * supportExtShort : 1|1|1|1|0|0|0|0|1|0|0|-1|0|-1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|0|-1|-1|-1|1|1|1|1|1|1|1|1|1|1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|-1|
             * deviceVersion : V5.4.0 build 160401
             * deviceCover : https://i.ys7.com/image/IPC/1.jpeg
             * cameraNum : 1
             * detectorNum : 0
             * cameraInfo : [{"deviceSerial":"695967184","cameraNo":1,"cameraName":"DS-2CD2810FWD(695967184)","isShared":"0","cameraCover":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","videoLevel":2,"videoQualityInfos":[{"videoQualityName":"流畅","videoLevel":0,"streamType":2},{"videoQualityName":"均衡","videoLevel":1,"streamType":2},{"videoQualityName":"高清","videoLevel":2,"streamType":1}]}]
             * detectorInfo : null
             */

            private String deviceSerial;
            private String deviceName;
            private String deviceType;
            private String category;
            private long addTime;
            private int status;
            private int isEncrypt;
            private int defence;
            private int alarmSoundMode;
            private int offlineNotify;
            private String supportExtShort;
            private String deviceVersion;
            private String deviceCover;
            private int cameraNum;
            private int detectorNum;
            private Object detectorInfo;
            private List<CameraInfoBean> cameraInfo;

            public String getDeviceSerial() {
                return deviceSerial;
            }

            public void setDeviceSerial(String deviceSerial) {
                this.deviceSerial = deviceSerial;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIsEncrypt() {
                return isEncrypt;
            }

            public void setIsEncrypt(int isEncrypt) {
                this.isEncrypt = isEncrypt;
            }

            public int getDefence() {
                return defence;
            }

            public void setDefence(int defence) {
                this.defence = defence;
            }

            public int getAlarmSoundMode() {
                return alarmSoundMode;
            }

            public void setAlarmSoundMode(int alarmSoundMode) {
                this.alarmSoundMode = alarmSoundMode;
            }

            public int getOfflineNotify() {
                return offlineNotify;
            }

            public void setOfflineNotify(int offlineNotify) {
                this.offlineNotify = offlineNotify;
            }

            public String getSupportExtShort() {
                return supportExtShort;
            }

            public void setSupportExtShort(String supportExtShort) {
                this.supportExtShort = supportExtShort;
            }

            public String getDeviceVersion() {
                return deviceVersion;
            }

            public void setDeviceVersion(String deviceVersion) {
                this.deviceVersion = deviceVersion;
            }

            public String getDeviceCover() {
                return deviceCover;
            }

            public void setDeviceCover(String deviceCover) {
                this.deviceCover = deviceCover;
            }

            public int getCameraNum() {
                return cameraNum;
            }

            public void setCameraNum(int cameraNum) {
                this.cameraNum = cameraNum;
            }

            public int getDetectorNum() {
                return detectorNum;
            }

            public void setDetectorNum(int detectorNum) {
                this.detectorNum = detectorNum;
            }

            public Object getDetectorInfo() {
                return detectorInfo;
            }

            public void setDetectorInfo(Object detectorInfo) {
                this.detectorInfo = detectorInfo;
            }

            public List<CameraInfoBean> getCameraInfo() {
                return cameraInfo;
            }

            public void setCameraInfo(List<CameraInfoBean> cameraInfo) {
                this.cameraInfo = cameraInfo;
            }

            public static class CameraInfoBean {
                /**
                 * deviceSerial : 695967184
                 * cameraNo : 1
                 * cameraName : DS-2CD2810FWD(695967184)
                 * isShared : 0
                 * cameraCover : https://i.ys7.com/assets/imgs/public/homeDevice.jpeg
                 * videoLevel : 2
                 * videoQualityInfos : [{"videoQualityName":"流畅","videoLevel":0,"streamType":2},{"videoQualityName":"均衡","videoLevel":1,"streamType":2},{"videoQualityName":"高清","videoLevel":2,"streamType":1}]
                 */

                private String deviceSerial;
                private int cameraNo;
                private String cameraName;
                private String isShared;
                private String cameraCover;
                private int videoLevel;
                private List<VideoQualityInfosBean> videoQualityInfos;

                public String getDeviceSerial() {
                    return deviceSerial;
                }

                public void setDeviceSerial(String deviceSerial) {
                    this.deviceSerial = deviceSerial;
                }

                public int getCameraNo() {
                    return cameraNo;
                }

                public void setCameraNo(int cameraNo) {
                    this.cameraNo = cameraNo;
                }

                public String getCameraName() {
                    return cameraName;
                }

                public void setCameraName(String cameraName) {
                    this.cameraName = cameraName;
                }

                public String getIsShared() {
                    return isShared;
                }

                public void setIsShared(String isShared) {
                    this.isShared = isShared;
                }

                public String getCameraCover() {
                    return cameraCover;
                }

                public void setCameraCover(String cameraCover) {
                    this.cameraCover = cameraCover;
                }

                public int getVideoLevel() {
                    return videoLevel;
                }

                public void setVideoLevel(int videoLevel) {
                    this.videoLevel = videoLevel;
                }

                public List<VideoQualityInfosBean> getVideoQualityInfos() {
                    return videoQualityInfos;
                }

                public void setVideoQualityInfos(List<VideoQualityInfosBean> videoQualityInfos) {
                    this.videoQualityInfos = videoQualityInfos;
                }

                public static class VideoQualityInfosBean {
                    /**
                     * videoQualityName : 流畅
                     * videoLevel : 0
                     * streamType : 2
                     */

                    private String videoQualityName;
                    private int videoLevel;
                    private int streamType;

                    public String getVideoQualityName() {
                        return videoQualityName;
                    }

                    public void setVideoQualityName(String videoQualityName) {
                        this.videoQualityName = videoQualityName;
                    }

                    public int getVideoLevel() {
                        return videoLevel;
                    }

                    public void setVideoLevel(int videoLevel) {
                        this.videoLevel = videoLevel;
                    }

                    public int getStreamType() {
                        return streamType;
                    }

                    public void setStreamType(int streamType) {
                        this.streamType = streamType;
                    }
                }
            }
        }
    }
}
