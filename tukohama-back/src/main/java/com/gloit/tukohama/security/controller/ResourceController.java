package com.gloit.tukohama.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/test")
public class ResourceController {

	@GetMapping(value = "/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin()
	{
		return "Permisos admin";
	}

	@GetMapping(value = "/editor")
	@PreAuthorize("hasRole('ROLE_EDITOR')")
	public String editor()
	{
		return "Permisos editor";
	}

	@GetMapping(value = "/invitado")
	@PreAuthorize("hasRole('ROLE_GUEST')")
	public String invitado()
	{
		return "Permisos invitado";
	}

}
