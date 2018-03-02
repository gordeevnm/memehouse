package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "picture")

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "orig_file_name")
	private String origFileName;
	@Column(name = "size")
	private long size;
	@Column(name = "mime_type")
	private String mimeType;
	@Column(name = "server_address")
	private String serverAddress;
	@Column(name = "size_orig")
	private String sizeOrig;
	@Column(name = "size_100")
	private String size100;
	@Column(name = "size_200")
	private String size200;
	@Column(name = "size_300")
	private String size300;
	@Column(name = "size_400")
	private String size400;
}