package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.CadastroInvalidoException;
import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Usuario;
import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.model.dto.UsuarioDTO;
import com.dh.trabalhoIntegrador.repository.PacienteRepository;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.IService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IService<Paciente, PacienteDTO> {

    @Autowired
    PacienteRepository pacienteRepository;



    public PacienteDTO buscar(Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RG inexistente na base de dados, verifique."));
        return mapper.convertValue(paciente, PacienteDTO.class);
    }


    @Override
    public List<PacienteDTO> buscarTodos() {
        List<Paciente> pacienteList = pacienteRepository.findAll();
        List<PacienteDTO> pacienteDTOList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        for(Paciente p : pacienteList){
            pacienteDTOList.add(mapper.convertValue(p, PacienteDTO.class));
        }
        return pacienteDTOList;
    }

    @Override
    public ResponseEntity deletar(Long id) throws ResourceNotFoundException {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado, verifique."));

       pacienteRepository.deleteById(id);
        return new ResponseEntity("Paciente "+ paciente.getNome() + "excluído com sucesso.", HttpStatus.OK);
    }

    public Paciente salvar(PacienteDTO paciente) throws CadastroInvalidoException, ResourceNotFoundException {
        Optional<Paciente> pacienteExists = pacienteRepository.findByRg(paciente.getRg());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Paciente pacienteSalvo = null;
        if(pacienteExists.isEmpty()){
            BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
            UsuarioDTO usuarioNovo = new UsuarioDTO();
            usuarioNovo.setUsername(paciente.getRg());
            usuarioNovo.setPassword(encrypt.encode(paciente.getUsuario().getPassword()));
            paciente.setDataCadastro(LocalDate.now());
            paciente.setUsuario(usuarioNovo);
            Paciente paciente1 = mapper.convertValue(paciente, Paciente.class);
            Usuario usuario = mapper.convertValue(usuarioNovo, Usuario.class);
            paciente1.setUsuario(usuario);
            pacienteSalvo = pacienteRepository.save(paciente1);
        }else{
            throw new CadastroInvalidoException("Rg já existente na base de dados.");
        }
            return pacienteSalvo;
    }

    public PacienteDTO buscarPorRg(String rg) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Optional<Paciente> paciente = pacienteRepository.findByRg(rg);

        if(paciente.isEmpty()){
            return null;
        }

        return mapper.convertValue(paciente.get(), PacienteDTO.class);
    }

    public PacienteDTO alteracaoPacial(PacienteDTO pacienteDTO){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Optional<Paciente> pacienteOptional = pacienteRepository.findByRg(pacienteDTO.getRg());
        if(pacienteOptional.isEmpty()){
            return null;
        }
        Paciente paciente = pacienteOptional.get();

        if(pacienteDTO.getNome() != null){
            paciente.setNome(pacienteDTO.getNome());
        }
        if(pacienteDTO.getRg() != null){
            paciente.setRg(pacienteDTO.getRg());
        }
        if(pacienteDTO.getEndereco() != null){
            paciente.setEndereco(pacienteDTO.getEndereco());
        }
        if(pacienteDTO.getSobrenome() != null){
            paciente.setSobrenome(pacienteDTO.getSobrenome());
        }

        return mapper.convertValue(pacienteRepository.save(paciente), PacienteDTO.class);
    }

    public PacienteDTO alteracaoTotal(PacienteDTO pacienteDTO) throws ResourceNotFoundException {

        Paciente paciente = pacienteRepository.findByRg(pacienteDTO.getRg()).orElseThrow(() -> {return new ResourceNotFoundException("RG inexistente na base de dados, verifique.");});

        Paciente pacienteUpdate = paciente;
        pacienteUpdate.setNome(pacienteDTO.getNome());
        pacienteUpdate.setSobrenome(pacienteDTO.getSobrenome());
        pacienteUpdate.setEndereco(pacienteDTO.getEndereco());
        pacienteUpdate.setRg(pacienteDTO.getRg());
        pacienteRepository.save(pacienteUpdate);
        return pacienteDTO;
    }
}
