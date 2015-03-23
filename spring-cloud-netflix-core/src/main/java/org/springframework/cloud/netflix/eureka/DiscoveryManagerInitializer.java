/*
 * Copyright 2013-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.netflix.eureka;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.EurekaClientConfig;

/**
 * @author Spencer Gibb
 */
public class DiscoveryManagerInitializer {

	@Autowired
	private EurekaClientConfig clientConfig;

	@Autowired
	private EurekaInstanceConfig instanceConfig;

	public synchronized void init() {
		if (DiscoveryManager.getInstance().getDiscoveryClient() == null) {
			DiscoveryManager.getInstance().initComponent(this.instanceConfig,
					this.clientConfig);
		}
		if (ApplicationInfoManager.getInstance().getInfo() == null) {
			ApplicationInfoManager.getInstance().initComponent(this.instanceConfig);
		}
	}

}
