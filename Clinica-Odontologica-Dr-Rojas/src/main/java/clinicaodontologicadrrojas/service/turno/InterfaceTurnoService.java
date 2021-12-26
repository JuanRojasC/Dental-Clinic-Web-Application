package clinicaodontologicadrrojas.service.turno;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InterfaceTurnoService<TurnoDTO> {

    public TurnoDTO guardarTurno(TurnoDTO p);
    public List<TurnoDTO> listarTurnos();
    public TurnoDTO actualizarTurno(TurnoDTO p);
    public Boolean eliminarTurno(Long id);
    public TurnoDTO buscarTurnoById(Long id);

}
