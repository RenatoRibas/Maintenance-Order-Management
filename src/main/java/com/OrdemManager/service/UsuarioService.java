package com.OrdemManager.service;

import com.OrdemManager.dto.UsuarioDTO;
import com.OrdemManager.dto.UsuarioResponseDTO;
import com.OrdemManager.exception.EntityNotFoundException;
import com.OrdemManager.model.Usuario;
import com.OrdemManager.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream().map(this::converterParaResponseDTO).collect(Collectors.toList());
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(id);

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setDescricao(usuarioAtualizado.getDescricao());

        return repository.save(usuarioExistente);
    }

    public void excluirUsuario(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

    public UsuarioResponseDTO converterParaResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setDescricao(usuario.getDescricao());
        return dto;
    }

    public Usuario converterParaEntidade(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setDescricao(dto.getDescricao());
        return usuario;
    }
}
