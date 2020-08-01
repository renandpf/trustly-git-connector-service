package br.com.pupposoft.trustly.connector.git.domains;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionFileInfos {
	private String extensionName;
	private List<FileInfo> files;
	
	public Long getAmountLines() {
		return this.files
			.stream()
			//.filter(fileInfo -> fileInfo.getLines() != null)
			.mapToLong(fileInfo -> fileInfo.getLines())
			.reduce(0, Long::sum);
	}
	
	public BigDecimal getAmountSizeInBytes() {
		
		final List<FileSize> allFileSize = this.files
		.stream()
		.map(fileInfo -> fileInfo.getFileSize())
		.collect(Collectors.toList());
		
		BigDecimal totalBytes = new BigDecimal("0.00");
		for (final FileSize fileSize : allFileSize) {
			if(Measurement.MBYTES.equals(fileSize.getMeasurement())) {
				totalBytes = totalBytes.add(fileSize.getSize().multiply(new BigDecimal("1048576")));
				
			}else if(Measurement.BYTES.equals(fileSize.getMeasurement())) {
				totalBytes = totalBytes.add(fileSize.getSize());
				
			}else {//Measurement.KBYTES
				totalBytes = totalBytes.add(fileSize.getSize().multiply(new BigDecimal("1024")));
			}
		}
		
		return totalBytes;
	}
}
