package persistencia;

import java.util.ArrayList;
import java.util.List;

import logica.Despacho;
import logica.Documento;
import logica.Remito;

public class DocumentoCrud {
    private final RemitoCrud remitoCrud;
    private final DespachoCrud despachoCrud;

    public DocumentoCrud(RemitoCrud remitoCrud, DespachoCrud despachoCrud) {
        if (remitoCrud == null || despachoCrud == null) {
            throw new IllegalArgumentException("Los CRUD de remitos y despachos no pueden ser nulos.");
        }
        this.remitoCrud = remitoCrud;
        this.despachoCrud = despachoCrud;
    }

    public List<Documento> obtenerDocumentos() {
        List<Documento> documentos = new ArrayList<>();
        documentos.addAll(remitoCrud.getRemitos());
        documentos.addAll(despachoCrud.getDespachos());
        return List.copyOf(documentos);
    }

    public void listarDocumentos() {
        List<Documento> documentos = obtenerDocumentos();
        if (documentos.isEmpty()) {
            System.out.println("No hay documentos cargados.");
            return;
        }

        for (Documento documento : documentos) {
            String tipo;
            if (documento instanceof Remito) {
                tipo = "REMITO";
            } else if (documento instanceof Despacho) {
                tipo = "DESPACHO";
            } else {
                tipo = documento.getTipo();
            }

            System.out.println("Tipo de documento: " + tipo);
            documento.mostrarInformacion();
            System.out.println("------------------------------");
        }
    }
}
