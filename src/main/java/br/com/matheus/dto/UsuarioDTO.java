package br.com.matheus.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UsuarioDTO {

	private String email;
	private String password;
	private String nome;
	private Date dataNascimento;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String validarUsuarioDTO(UsuarioDTO user) {

		String messages = "";

		String regex = "^[\\w+.]+@\\w+\\.\\w{2,}(?:\\.\\w{2})?$";

		if (user.getEmail() == null || "".equals(user.getEmail())) {
			messages = messages + "Campo EMAIL é obrigatório. ";
		}

		if (!user.getEmail().matches(regex)) {
			messages = messages + "Campo EMAIL está com formato incorreto. ";
		}

		if (user.getNome() == null || "".equals(user.getNome())) {
			messages = messages + "Campo NOME é obrigatório. ";
		}

		if (user.getPassword() == null || "".equals(user.getPassword())) {
			messages = messages + "Campo PASSWORD é obrigatório. ";
		}

		if (user.getDataNascimento() == null) {
			messages = messages + "O campo Data de Nacismento é obrigatório. ";
		}

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String data = sdf.format(user.getDataNascimento());
			user.setDataNascimento(sdf.parse(data));

		} catch (Exception e) {

			messages = messages
					+ "Ocorreu erro ao tratar do campo DATA NASCIMENTO. Por favor verifique se está no Formato esperado: yyyy-MM-dd";

		}

		return messages;
	}

}
