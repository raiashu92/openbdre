/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wipro.ats.bdre.md.beans;

import com.wipro.ats.bdre.annotation.PersistableParam;

/**
 * Created by IshitaParekh on 18-02-2015.
 */

/**
 * This class contains all the setter and getter methods for SuccessFailure  variables.
 */
public class SuccessFailureInfo {
    @PersistableParam(keyName = "success-count")
    private Integer countSuccess = 0;
    @PersistableParam(keyName = "failure-count")
    private Integer countFailure = 0;
    @PersistableParam(keyName = "time")
    private Integer time = 0;
    
    public Integer getCountSuccess() {
        return countSuccess;
    }

    public void setCountSuccess(Integer countSuccess) {
        this.countSuccess = countSuccess;
    }

    public Integer getCountFailure() {
        return countFailure;
    }

    public void setCountFailure(Integer countFailure) {
        this.countFailure = countFailure;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return " countSuccess:" + countSuccess + " countFailure:" + countFailure + " time:" + time;
    }

}

