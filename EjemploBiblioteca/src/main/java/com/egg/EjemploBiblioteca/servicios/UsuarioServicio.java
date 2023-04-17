package com.egg.EjemploBiblioteca.servicios;

import com.egg.EjemploBiblioteca.entidades.Imagen;
import com.egg.EjemploBiblioteca.entidades.Libro;
import com.egg.EjemploBiblioteca.entidades.Usuario;
import com.egg.EjemploBiblioteca.enumeraciones.Rol;
import com.egg.EjemploBiblioteca.excepciones.MiException;
import com.egg.EjemploBiblioteca.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private ImagenServicio imagenServicio;
    
    @Transactional
    public void registrar( MultipartFile archivo, String nombre, String email, String password, String password2) throws MiException{

        validar(nombre, email, password, password2);
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        
        usuario.setRol(Rol.USER);
        
        Imagen imagen = imagenServicio.guardar(archivo);
        
        usuario.setImagen(imagen);
        
        usuarioRepositorio.save(usuario);
    
}
    
    
       @Transactional
    public void actualizar( MultipartFile archivo, String idUsuario, String nombre, String email, String password, String password2) throws MiException{

        validar(nombre, email, password, password2);
        
        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);
        
        if (respuesta.isPresent()){
            
             Usuario usuario = respuesta.get();
             usuario.setNombre(nombre);
             usuario.setEmail(email);
             
             usuario.setPassword(new BCryptPasswordEncoder().encode(password));
            
            
            
            String idImagen = null;
            
            if(usuario.getImagen() != null){
                idImagen = usuario.getImagen().getId();
            }
            
            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            
            usuario.setImagen(imagen);
            
             usuarioRepositorio.save(usuario);
            
        }
        
}
    
    private void validar(String nombre, String email, String password, String password2) throws MiException{

if(nombre.isEmpty() || nombre == null){
    throw new MiException("El nombre no puede ser nulo o estar vacío");
}

if(email.isEmpty() || email == null){
    throw new MiException("El email no puede ser nulo o estar vacío");
}

if(password.isEmpty() || password == null || password.length() <=5){
    throw new MiException("El password no puede ser nulo, estar vacío o tener menos de 5 dígitos");
}

if(!password.equals(password2)){
    throw new MiException("Las contraseñas ingresadas deben ser iguales");
}
        
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
     
     if(usuario != null){
         
         List<GrantedAuthority> permisos = new ArrayList();
         
         GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
         
         permisos.add(p);
         
         ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
         
         HttpSession session = attr.getRequest().getSession(true);
         
         session.setAttribute("usuariosession", usuario);
         
         return new User(usuario.getEmail(), usuario.getPassword(), permisos);
         
     }else{
         return null;
     }
     
    }
    
    public Usuario getone(String id){
        return  usuarioRepositorio.getOne(id);
        
    }
    
    
      public void eliminarUsuario(String id) throws MiException{

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        
        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();
            
            usuarioRepositorio.delete(usuario);

        }
    }
      
       public List<Usuario> listarUsuarios() {

        List<Usuario> usuario = new ArrayList();

        usuario = usuarioRepositorio.findAll();
        
        return usuario;
    }
    
    


}
