package com.mcoding.comp.license.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceUtils {
	
	public static List<String> getProcessorId() {
		String cpuInfo = exeCmd("wmic cpu");
		String[] lines = cpuInfo.split("\n");
		
		List<String> titles = new ArrayList<>();
		
		Pattern pattern = Pattern.compile("(.+?\\s)(DeviceID\\s+)(.+?)(ProcessorId\\s+).", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(lines[0]);
		
		while (matcher.find()) {
			titles.add(matcher.group(1));
			titles.add(matcher.group(2));
			titles.add(matcher.group(3));
			titles.add(matcher.group(4));
		}
		
		if (titles.size() == 0) {
			return null;
		}
		
		List<String> cpuInfoList = new ArrayList<>();
		for(int i=1; lines.length > 1 && i<lines.length; i++){
			if (lines[i].trim().length() <= 0 ) {
				continue;
			}
			
			int deviceIdbeginIndex = titles.get(0).length();
			int deviceIdEndIndex = deviceIdbeginIndex + titles.get(1).length();
			
			String deviceId = "DeviceID:" + lines[i].substring(deviceIdbeginIndex, deviceIdEndIndex).trim();
			
			int processorIdBeginIndex = deviceIdEndIndex + titles.get(2).length();
			int processorIdEndIndex = processorIdBeginIndex + titles.get(3).length();
			
			String processorId = "ProcessorId:" + lines[i].substring(processorIdBeginIndex, processorIdEndIndex).trim();
			cpuInfoList.add(deviceId + "," + processorId);
			
		}
		
		return cpuInfoList;
	}

	public static List<String> getDiskSerialNumber() {
		String diskInfo = exeCmd("wmic diskdrive");
        String[] lines = diskInfo.split("\n");
		
		List<String> titles = new ArrayList<>();
		
		Pattern pattern = Pattern.compile("(.+?\\s)(Caption\\s+)(.+?)(SerialNumber\\s+).", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(lines[0]);
		
		while (matcher.find()) {
			titles.add(matcher.group(1));
			titles.add(matcher.group(2));
			titles.add(matcher.group(3));
			titles.add(matcher.group(4));
		}
		
		if (titles.size() == 0) {
			return null;
		}
		
		List<String> diskInfoList = new ArrayList<>();
		for(int i=1; lines.length > 1 && i<lines.length; i++){
			if (lines[i].trim().length() <= 0 ) {
				continue;
			}
			
			int captionBeginIndex = titles.get(0).length();
			int captionEndIndex = captionBeginIndex + titles.get(1).length();
			
			String caption = "Caption:" + lines[i].substring(captionBeginIndex, captionEndIndex).trim();
			
			int serialNumberBeginIndex = captionEndIndex + titles.get(2).length();
			int serialNumberEndIndex = serialNumberBeginIndex + titles.get(3).length();
			
			String serialNumber = "SerialNumber:" + lines[i].substring(serialNumberBeginIndex, serialNumberEndIndex).trim();
			diskInfoList.add(caption + "," + serialNumber);
			
		}
		
		return diskInfoList;
	}

	public static List<String> getMacAddresses() {
		String diskInfo = exeCmd("wmic nic list brief");
        String[] lines = diskInfo.split("\n");
		
		List<String> titles = new ArrayList<>();
		
		Pattern pattern = Pattern.compile("(.+?\\s)(MACAddress\\s+)(Name\\s+).", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(lines[0]);
		
		while (matcher.find()) {
			titles.add(matcher.group(1));
			titles.add(matcher.group(2));
			titles.add(matcher.group(3));
		}
		
		if (titles.size() == 0) {
			return null;
		}
		
		List<String> macInfoList = new ArrayList<>();
		for(int i=1; lines.length > 1 && i<lines.length; i++){
			if (lines[i].trim().length() <= 0 ) {
				continue;
			}
			
			int macAddressBeginIndex = titles.get(0).length();
			int macAddressEndIndex = macAddressBeginIndex + titles.get(1).length();
			
			String macAddress = lines[i].substring(macAddressBeginIndex, macAddressEndIndex).trim();
			
			if (macAddress == null || macAddress.trim().equals("")) {
				continue;
			}
			macAddress = "macAddress:" + macAddress;
			
			int nameBeginIndex = macAddressEndIndex;
			int nameEndIndex = nameBeginIndex + titles.get(2).length();
			
			String name = "name:" + lines[i].substring(nameBeginIndex, nameEndIndex).trim();
			macInfoList.add(name + "," + macAddress);
		}
		
		return macInfoList;
	}

	public static String exeCmd(String commandStr) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			Process p = Runtime.getRuntime().exec(commandStr);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}
	
}
