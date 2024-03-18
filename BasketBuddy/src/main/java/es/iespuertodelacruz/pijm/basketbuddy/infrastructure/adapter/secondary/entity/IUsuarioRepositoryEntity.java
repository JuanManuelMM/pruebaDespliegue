package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.entity;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IUsuarioRepositoryEntity extends JpaRepository<UsuarioEntity, Integer>{
	
	@Query(value = "SELECT * FROM usuarios as u WHERE u.nombre = :nombre", nativeQuery = true)
    UsuarioEntity findByName(String nombre);
	
	@Query(value = "SELECT * FROM usuarios as u WHERE u.nick = :nick", nativeQuery = true)
    UsuarioEntity findByNick(String nick);
	
	@Query("SELECT u from UsuarioEntity u where u.email=:email")
    public UsuarioEntity findByEmail(@Param("email") String email);
	
	@Modifying
	@Query(value="update usuarios set usuarios.activo = 1 where usuarios.email = :email", nativeQuery = true)
	public void actualizarActiva(@Param("email") String email);
}
