package gm.zona_fit.controllers;
import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    private IClienteServicio clienteServicio;

    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteServicio.listarClientes();
    }

    @PostMapping("/clientes")
    public Cliente guardarCliente(@RequestBody Cliente cliente){
        return clienteServicio.guardarCliente(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable int id){
        Cliente cliente = clienteServicio.buscarClientePorID(id);
        if(cliente !=null){
            return ResponseEntity.ok(cliente);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente clienteDetalles){

        //Buscamos si el cliente existe en la base de datos
        Cliente clienteExistente = clienteServicio.buscarClientePorID(id);

        if(clienteExistente != null){
            //Datos nuevos que vienen en el JSON
            clienteExistente.setNombre(clienteDetalles.getNombre());
            clienteExistente.setApellido(clienteDetalles.getApellido());
            clienteExistente.setMembresia(clienteDetalles.getMembresia());

            //Guardamos los cambios usando el mismo metodo que usamos para crear
            Cliente clienteActualizado = clienteServicio.guardarCliente(clienteExistente);

            //Devolvemos el cliente ya modificado
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Integer id){
        Cliente clienteExistente = clienteServicio.buscarClientePorID(id);
        if(clienteExistente != null){
            clienteServicio.eliminarCliente(clienteExistente);

            //JSON de respuesta
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("Eliminado", true);

            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
