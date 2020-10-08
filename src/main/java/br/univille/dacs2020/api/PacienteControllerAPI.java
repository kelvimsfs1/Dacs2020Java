package br.univille.dacs2020.api;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.univille.dacs2020.model.Paciente;
import br.univille.dacs2020.service.PacienteService;
@RestController@RequestMapping("/api/paciente")
public class PacienteControllerAPI {   
     @Autowired    
     private PacienteService service;

    @GetMapping   
   public ResponseEntity<List<Paciente>> getAll(){        
List<Paciente> lista = service.getAll();        
  return new ResponseEntity<List<Paciente>>(lista,HttpStatus.OK);    
   }
    @GetMapping("/{id}")   
     public ResponseEntity<Paciente> getById(@PathVariable("id") Paciente paciente){  
             return new ResponseEntity<>(paciente, HttpStatus.OK);  
              }


   @PostMapping
   public ResponseEntity<Paciente> save(@RequestBody Paciente paciente){

       service.save(paciente);
       return new ResponseEntity<Paciente>(paciente,HttpStatus.OK);
     }

   @PutMapping("/{id}")
   public ResponseEntity<Paciente> update(@PathVariable("id") Paciente pacienteAntigo,
   @RequestBody Paciente PacienteAtualizado){
    
        pacienteAntigo.setNome(PacienteAtualizado.getNome());
        pacienteAntigo.setSexo(PacienteAtualizado.getSexo());
        pacienteAntigo.setDataNascimento(PacienteAtualizado.getDataNascimento());

        service.save(pacienteAntigo);
        return new ResponseEntity<Paciente>(pacienteAntigo,HttpStatus.OK);



   }

   @DeleteMapping
   public ResponseEntity<?> delete(@PathVariable("id") Paciente paciente){
     if(paciente==null){
       return ResponseEntity.notFound().build();
     }
    service.delete(paciente);
    return ResponseEntity.ok().build();
   }

    


}