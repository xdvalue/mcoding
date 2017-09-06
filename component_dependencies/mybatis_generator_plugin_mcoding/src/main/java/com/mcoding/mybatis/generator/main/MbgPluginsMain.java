package com.mcoding.mybatis.generator.main;

import org.mybatis.generator.api.ShellRunner;

public class MbgPluginsMain {

	public static void main(String[] args) {
		/**
		 * 填写 配置文件的路径
		 */
//		args = new String[] { "-configfile", "/Users/hzy/work/code/mcoding/tools/generator/generator.xml", "-overwrite" };
		args = new String[] { "-configfile", "/Users/hzy/work/code/mcoding/code/trunk/component_dependencies/mybatis_generator_plugin_mcoding/src/main/resources/generator.xml", "-overwrite" };

		ShellRunner.main(args);
	}

}
