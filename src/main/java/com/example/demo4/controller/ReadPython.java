package com.example.demo4.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;
@Component
public class ReadPython {
	public String ReadPython(Double ProductBrand, Double Material, Double ProductionWay) throws IOException, InterruptedException {
		String data = "C:\\Users\\ADMIN\\Downloads\\test\\PhatTrienHeThongThongMinh\\back-end\\demo4\\src\\main\\resources\\python\\AI.sav";
		ProcessBuilder pb = new ProcessBuilder("python",
				"C:\\Users\\ADMIN\\Downloads\\test\\PhatTrienHeThongThongMinh\\back-end\\demo4\\src\\main\\resources\\python\\load_AI.py",
				data, ProductBrand.toString(),Material.toString(),ProductionWay.toString());
		Process p = pb.start();
		p.waitFor();
		BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		String label="";
		while ((line = bfr.readLine()) != null) {
//			System.out.println(line);
			label=line;
		}
		return label;
	}

}
