from pathlib import Path
from datetime import date

from reportlab.lib import colors
from reportlab.lib.enums import TA_CENTER
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import cm
from reportlab.platypus import (
    SimpleDocTemplate,
    Paragraph,
    Spacer,
    Table,
    TableStyle,
    PageBreak,
)


PROJECT_DIR = Path(__file__).resolve().parent
OUTPUT_PDF = PROJECT_DIR / "Informe_Funcionalidades_DocuGestion.pdf"


def page_header_footer(canvas, doc):
    canvas.saveState()
    width, height = A4

    canvas.setFillColor(colors.HexColor("#DCE6F1"))
    canvas.rect(0, height - 1.15 * cm, width, 1.15 * cm, fill=1, stroke=0)
    canvas.setFillColor(colors.HexColor("#1F4E78"))
    canvas.setFont("Helvetica-Bold", 9)
    canvas.drawString(1.5 * cm, height - 0.75 * cm, "DocuGestion - Informe de funcionalidades")

    canvas.setStrokeColor(colors.HexColor("#A5A5A5"))
    canvas.line(1.5 * cm, 1.25 * cm, width - 1.5 * cm, 1.25 * cm)
    canvas.setFillColor(colors.HexColor("#666666"))
    canvas.setFont("Helvetica", 8)
    canvas.drawString(1.5 * cm, 0.82 * cm, "Aplicacion Java por consola")
    canvas.drawRightString(width - 1.5 * cm, 0.82 * cm, f"Pagina {doc.page}")
    canvas.restoreState()


def paragraph(text, style):
    return Paragraph(text, style)


styles = getSampleStyleSheet()
styles.add(ParagraphStyle(
    name="CoverTitle",
    parent=styles["Title"],
    fontSize=24,
    leading=30,
    alignment=TA_CENTER,
    textColor=colors.HexColor("#1F4E78"),
    spaceAfter=18,
))
styles.add(ParagraphStyle(
    name="CoverSubtitle",
    parent=styles["Normal"],
    fontSize=12,
    leading=18,
    alignment=TA_CENTER,
    textColor=colors.HexColor("#404040"),
))
styles.add(ParagraphStyle(
    name="Section",
    parent=styles["Heading1"],
    fontSize=16,
    leading=20,
    textColor=colors.HexColor("#1F4E78"),
    spaceBefore=8,
    spaceAfter=10,
))
styles.add(ParagraphStyle(
    name="Subsection",
    parent=styles["Heading2"],
    fontSize=12,
    leading=15,
    textColor=colors.HexColor("#2F75B5"),
    spaceBefore=7,
    spaceAfter=5,
))


def bullets(items):
    return [paragraph(f"• {item}", styles["BodyText"]) for item in items]


story = []

# Portada
story.append(Spacer(1, 3.5 * cm))
story.append(paragraph("Informe de funcionalidades del sistema", styles["CoverTitle"]))
story.append(paragraph("DocuGestion", styles["CoverTitle"]))
story.append(Spacer(1, 0.4 * cm))
story.append(paragraph(
    "Documento detallado de los modulos, operaciones, roles y reglas de negocio "
    "implementados en la aplicacion Java.",
    styles["CoverSubtitle"],
))
story.append(Spacer(1, 2 * cm))

cover_table = Table([
    ["Proyecto", "DocuGestion"],
    ["Tipo de sistema", "Gestion administrativa y logistica por consola"],
    ["Tecnologia", "Java 17+ / Programacion Orientada a Objetos"],
    ["Fecha del informe", date.today().strftime("%d/%m/%Y")],
], colWidths=[4.2 * cm, 10.5 * cm])
cover_table.setStyle(TableStyle([
    ("BACKGROUND", (0, 0), (0, -1), colors.HexColor("#DCE6F1")),
    ("TEXTCOLOR", (0, 0), (-1, -1), colors.HexColor("#333333")),
    ("FONTNAME", (0, 0), (0, -1), "Helvetica-Bold"),
    ("GRID", (0, 0), (-1, -1), 0.5, colors.HexColor("#A5A5A5")),
    ("VALIGN", (0, 0), (-1, -1), "MIDDLE"),
    ("ROWBACKGROUNDS", (1, 0), (1, -1),
     [colors.white, colors.HexColor("#F7F9FC")]),
    ("TOPPADDING", (0, 0), (-1, -1), 8),
    ("BOTTOMPADDING", (0, 0), (-1, -1), 8),
]))
story.append(cover_table)

story.append(PageBreak())

# Resumen ejecutivo
story.append(paragraph("1. Resumen ejecutivo", styles["Section"]))
story.append(paragraph(
    "DocuGestion es una aplicacion Java interactiva que permite autenticar usuarios "
    "mediante roles y administrar despachos, remitos, productos y cuentas de usuario. "
    "Toda la informacion se conserva durante la ejecucion de la aplicacion; no utiliza "
    "base de datos ni almacenamiento persistente.",
    styles["BodyText"],
))

story.append(paragraph("Objetivos del sistema", styles["Subsection"]))
story.extend(bullets([
    "Controlar el acceso segun el cargo del usuario.",
    "Registrar despachos asociados a un remito.",
    "Relacionar remitos, productos y detalles de mercaderia.",
    "Administrar cuentas de usuario y proteger al unico administrador.",
    "Consultar historiales utilizando un modelo polimorfico de documentos.",
]))

story.append(paragraph("Alcance funcional", styles["Subsection"]))
story.append(paragraph(
    "Las operaciones cubren autenticacion, gestion de usuarios, carga de despachos, "
    "modificacion del estado operativo, eliminacion controlada y consulta de historiales.",
    styles["BodyText"],
))

story.append(paragraph("Limitaciones conocidas", styles["Subsection"]))
story.extend(bullets([
    "Los datos se pierden al cerrar el programa.",
    "La interfaz es de consola y no posee interfaz grafica.",
    "Las contrasenas se almacenan sin cifrado.",
    "No existe exportacion, auditoria ni inicio de sesion persistente.",
]))

story.append(PageBreak())

# Arquitectura
story.append(paragraph("2. Arquitectura y organizacion", styles["Section"]))
story.append(paragraph(
    "El codigo se divide por responsabilidades en paquetes: "
    "<b>app</b> inicia la aplicacion, <b>igu</b> muestra menus y captura opciones, "
    "<b>persistencia</b> administra las colecciones, <b>logica</b> contiene las entidades "
    "de negocio y <b>utils</b> concentra la entrada por consola.",
    styles["BodyText"],
))

architecture = [
    ["Paquete", "Responsabilidad principal", "Elementos destacados"],
    ["app", "Punto de inicio", "Main"],
    ["igu", "Interaccion con el usuario", "Login, MenuPrincipal, MenuUsers"],
    ["persistencia", "Operaciones CRUD y consultas", "UsuariosCrud, DespachoCrud"],
    ["logica", "Modelo de dominio", "Persona, Trabajador, Usuarios, Documento, Remito, Despacho, Producto, Detalle"],
    ["utils", "Lectura de datos por consola", "Utiles"],
]
t = Table(architecture, colWidths=[2.5 * cm, 5 * cm, 7.5 * cm], repeatRows=1)
t.setStyle(TableStyle([
    ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#1F4E78")),
    ("TEXTCOLOR", (0, 0), (-1, 0), colors.white),
    ("FONTNAME", (0, 0), (-1, 0), "Helvetica-Bold"),
    ("GRID", (0, 0), (-1, -1), 0.5, colors.HexColor("#B4C6E7")),
    ("VALIGN", (0, 0), (-1, -1), "TOP"),
    ("ROWBACKGROUNDS", (0, 1), (-1, -1),
     [colors.white, colors.HexColor("#F2F6FA")]),
    ("TOPPADDING", (0, 0), (-1, -1), 6),
    ("BOTTOMPADDING", (0, 0), (-1, -1), 6),
]))
story.append(t)

story.append(paragraph("Modelo de relaciones", styles["Subsection"]))
story.extend(bullets([
    "Persona es la clase abstracta base de los trabajadores.",
    "Trabajador hereda de Persona y Usuarios hereda de Trabajador.",
    "Documento es abstracta e implementa la interfaz Imprimible.",
    "Remito y Despacho heredan de Documento.",
    "Despacho contiene una referencia obligatoria a Remito.",
    "Remito contiene una coleccion de Detalle.",
    "Detalle contiene una referencia a Producto.",
]))

story.append(PageBreak())

# Inicio de sesion
story.append(paragraph("3. Inicio de sesion y control de acceso", styles["Section"]))
story.append(paragraph("Flujo operativo", styles["Subsection"]))
story.extend(bullets([
    "La aplicacion solicita el nombre de usuario.",
    "Solicita la contrasena.",
    "Validaciona las credenciales contra la lista de usuarios registrados.",
    "Si son correctas, obtiene el cargo del usuario.",
    "El cargo determina el menu disponible.",
    "Permite hasta tres intentos fallidos antes de cerrar la aplicacion.",
]))

story.append(paragraph("Usuarios precargados", styles["Subsection"]))
users = [
    ["Usuario", "Contrasena", "Rol"],
    ["admin", "admin123", "Administrador"],
    ["vendedor", "venta123", "Vendedor"],
]
tu = Table(users, colWidths=[5 * cm, 5 * cm, 5 * cm], repeatRows=1)
tu.setStyle(TableStyle([
    ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#1F4E78")),
    ("TEXTCOLOR", (0, 0), (-1, 0), colors.white),
    ("FONTNAME", (0, 0), (-1, 0), "Helvetica-Bold"),
    ("GRID", (0, 0), (-1, -1), 0.5, colors.HexColor("#B4C6E7")),
    ("ROWBACKGROUNDS", (0, 1), (-1, -1),
     [colors.white, colors.HexColor("#F2F6FA")]),
    ("TOPPADDING", (0, 0), (-1, -1), 7),
    ("BOTTOMPADDING", (0, 0), (-1, -1), 7),
]))
story.append(tu)
story.append(Spacer(1, 8))
story.append(paragraph(
    "<b>Nota de seguridad:</b> los usuarios demo existen para facilitar las pruebas. "
    "Las credenciales son valores iniciales en memoria y no representan autenticacion segura.",
    styles["BodyText"],
))

story.append(PageBreak())

# Roles
story.append(paragraph("4. Funcionalidades segun el rol", styles["Section"]))
role_data = [
    ["Rol", "Operaciones disponibles"],
    ["Administrador", "Ver despachos; eliminar despacho; gestionar usuarios; consultar historial general."],
    ["Vendedor", "Ver despachos; consultar historial general."],
    ["Logistica", "Ver despachos; consultar historial general."],
    ["Operario", "Cargar despacho; ver despachos; modificar despacho; consultar historial general."],
]
tr = Table(role_data, colWidths=[3.2 * cm, 11.8 * cm], repeatRows=1)
tr.setStyle(TableStyle([
    ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#1F4E78")),
    ("TEXTCOLOR", (0, 0), (-1, 0), colors.white),
    ("FONTNAME", (0, 0), (-1, 0), "Helvetica-Bold"),
    ("GRID", (0, 0), (-1, -1), 0.5, colors.HexColor("#B4C6E7")),
    ("VALIGN", (0, 0), (-1, -1), "TOP"),
    ("ROWBACKGROUNDS", (0, 1), (-1, -1),
     [colors.white, colors.HexColor("#F2F6FA")]),
    ("TOPPADDING", (0, 0), (-1, -1), 7),
    ("BOTTOMPADDING", (0, 0), (-1, -1), 7),
]))
story.append(tr)

story.append(paragraph("Gestion del administrador", styles["Subsection"]))
story.extend(bullets([
    "Crear, listar, buscar, actualizar y eliminar usuarios.",
    "Consultar todos los despachos registrados.",
    "Eliminar un despacho seleccionado por su identificador.",
    "No puede eliminarse el unico usuario administrador activo.",
]))

story.append(paragraph("Gestion del operario", styles["Subsection"]))
story.extend(bullets([
    "Crear remitos y agregar productos a cada remito.",
    "Crear el despacho asociado al remito.",
    "Consultar y modificar datos de los despachos.",
    "Marcar un despacho como entregado.",
]))

story.append(PageBreak())

# Despachos
story.append(paragraph("5. Gestion de despachos y remitos", styles["Section"]))

story.append(paragraph("Carga de un despacho", styles["Subsection"]))
story.extend(bullets([
    "Se solicita el numero del remito.",
    "Se crea un remito nuevo con fecha actual y entrega estimada al dia siguiente.",
    "Se solicitan codigo, nombre y cantidad de cada producto.",
    "Cada producto se incorpora al remito mediante un objeto Detalle.",
    "Se pregunta si se desea continuar agregando productos.",
    "Se registran operario, cantidad de bultos y una nota.",
    "Se crea el despacho y se marca el remito como preparado.",
]))

story.append(paragraph("Datos administrados por cada despacho", styles["Subsection"]))
dispatch_data = [
    ["Dato", "Descripcion", "Validacion destacada"],
    ["Remito", "Documento asociado al despacho", "No puede ser nulo ni ya tener despacho"],
    ["Operario", "Persona responsable", "No puede quedar vacio"],
    ["Bultos", "Cantidad de unidades", "No admite valores negativos"],
    ["Nota", "Observacion adicional", "Se normaliza a texto vacio si es nulo"],
    ["Entregado", "Estado de entrega", "Se puede modificar o marcar como entregado"],
]
td = Table(dispatch_data, colWidths=[2.5 * cm, 6.5 * cm, 6 * cm], repeatRows=1)
td.setStyle(TableStyle([
    ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#1F4E78")),
    ("TEXTCOLOR", (0, 0), (-1, 0), colors.white),
    ("FONTNAME", (0, 0), (-1, 0), "Helvetica-Bold"),
    ("GRID", (0, 0), (-1, -1), 0.5, colors.HexColor("#B4C6E7")),
    ("VALIGN", (0, 0), (-1, -1), "TOP"),
    ("ROWBACKGROUNDS", (0, 1), (-1, -1),
     [colors.white, colors.HexColor("#F2F6FA")]),
    ("TOPPADDING", (0, 0), (-1, -1), 6),
    ("BOTTOMPADDING", (0, 0), (-1, -1), 6),
]))
story.append(td)

story.append(paragraph("Consulta y busqueda", styles["Subsection"]))
story.extend(bullets([
    "Listar muestra toda la informacion de cada despacho y remito asociado.",
    "Buscar por ID localiza un despacho exacto.",
    "Buscar por numero de remito devuelve el despacho vinculado.",
]))

story.append(PageBreak())

# Modificacion y eliminacion
story.append(paragraph("6. Modificacion y eliminacion", styles["Section"]))
story.append(paragraph("Actualizar despacho", styles["Subsection"]))
story.extend(bullets([
    "Modificar el nombre del operario.",
    "Cambiar la cantidad de bultos.",
    "Modificar la nota.",
    "Marcar el despacho como entregado.",
    "Finalizar el proceso sin necesidad de guardar manualmente.",
]))

story.append(paragraph("Eliminar despacho", styles["Subsection"]))
story.append(paragraph(
    "El administrador puede seleccionar el ID del despacho. La eliminacion retira el objeto "
    "de la coleccion y desmarca el remito asociado, permitiendo que dicho remito vuelva a "
    "estar disponible para otra operacion.",
    styles["BodyText"],
))

story.append(paragraph("Mensajes de validacion", styles["Subsection"]))
story.extend(bullets([
    "No se permiten despachos con remito nulo.",
    "Un remito preparado no admite un segundo despacho.",
    "No se permite asignar cantidades negativas de bultos.",
    "Las opciones invalidas son informadas sin detener el programa.",
]))

story.append(PageBreak())

# Usuarios
story.append(paragraph("7. Gestion de usuarios", styles["Section"]))
story.append(paragraph(
    "El modulo de usuarios permite administrar cuentas de persona trabajadora desde un "
    "submenu exclusivo del administrador.",
    styles["BodyText"],
))

story.append(paragraph("Operaciones disponibles", styles["Subsection"]))
user_ops = [
    ["Operacion", "Descripcion"],
    ["Crear", "Registra DNI, nombre, apellido, email, telefono, cargo, salario, usuario y contrasena."],
    ["Listar", "Muestra todos los usuarios registrados en memoria."],
    ["Buscar", "Localiza un usuario por su ID numerico."],
    ["Actualizar", "Permite modificar datos personales, cargo, salario, usuario y contrasena."],
    ["Eliminar", "Retira una cuenta; protege al unico administrador registrado."],
]
to = Table(user_ops, colWidths=[3.2 * cm, 11.8 * cm], repeatRows=1)
to.setStyle(TableStyle([
    ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#1F4E78")),
    ("TEXTCOLOR", (0, 0), (-1, 0), colors.white),
    ("FONTNAME", (0, 0), (-1, 0), "Helvetica-Bold"),
    ("GRID", (0, 0), (-1, -1), 0.5, colors.HexColor("#B4C6E7")),
    ("VALIGN", (0, 0), (-1, -1), "TOP"),
    ("ROWBACKGROUNDS", (0, 1), (-1, -1),
     [colors.white, colors.HexColor("#F2F6FA")]),
    ("TOPPADDING", (0, 0), (-1, -1), 7),
    ("BOTTOMPADDING", (0, 0), (-1, -1), 7),
]))
story.append(to)

story.append(paragraph("Reglas de integridad", styles["Subsection"]))
story.extend(bullets([
    "Todos los campos obligatorios deben tener contenido.",
    "No pueden existir dos usuarios con el mismo nombre de acceso.",
    "La comparacion de nombres de usuario no distingue mayusculas.",
    "Al modificar el nombre de acceso se verifica que no este ocupado.",
    "No se elimina el unico administrador para evitar dejar el sistema sin administracion.",
]))

story.append(PageBreak())

# Polimorfismo e historial
story.append(paragraph("8. Historial general y polimorfismo", styles["Section"]))
story.append(paragraph(
    "La opcion de historial general construye una coleccion polimorfica de "
    "<b>Documento</b>. Esta coleccion contiene Remitos y Despachos aunque todos "
    "se almacenan mediante su supertipo comun.",
    styles["BodyText"],
))
story.extend(bullets([
    "Cada remito se agrega al historial.",
    "Cada despacho se agrega al historial.",
    "Se utiliza obtenerResumen() para mostrar una descripcion general.",
    "Se utiliza mostrarInformacion() para mostrar los datos completos.",
    "El metodo ejecutado corresponde al tipo real del objeto: Remito o Despacho.",
]))

story.append(paragraph("Datos mostrados", styles["Subsection"]))
story.extend(bullets([
    "Remito: numero, fechas, estado preparado, productos y cantidades.",
    "Despacho: ID, fecha, remito asociado, operario, bultos, nota y estado entregado.",
]))

story.append(PageBreak())

# POO
story.append(paragraph("9. Elementos de Programacion Orientada a Objetos", styles["Section"]))
oop_data = [
    ["Principio", "Aplicacion en el sistema"],
    ["Encapsulamiento", "Atributos privados o protegidos y acceso mediante getters y setters."],
    ["Herencia", "Trabajador hereda de Persona; Remito y Despacho heredan de Documento."],
    ["Abstraccion", "Persona y Documento contienen comportamiento abstracto."],
    ["Polimorfismo", "Historial de Documento que invoca metodos de Remito y Despacho."],
    ["Interfaz", "Persona y Documento implementan Imprimible."],
    ["Composicion", "Despacho contiene Remito; Remito contiene Detalle; Detalle contiene Producto."],
    ["Sobrecarga", "Producto posee constructores sin cantidad y con cantidad."],
    ["Colecciones", "ArrayList mantiene usuarios, despachos y detalles de un remito."],
]
top = Table(oop_data, colWidths=[3.4 * cm, 11.6 * cm], repeatRows=1)
top.setStyle(TableStyle([
    ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#1F4E78")),
    ("TEXTCOLOR", (0, 0), (-1, 0), colors.white),
    ("FONTNAME", (0, 0), (-1, 0), "Helvetica-Bold"),
    ("GRID", (0, 0), (-1, -1), 0.5, colors.HexColor("#B4C6E7")),
    ("VALIGN", (0, 0), (-1, -1), "TOP"),
    ("ROWBACKGROUNDS", (0, 1), (-1, -1),
     [colors.white, colors.HexColor("#F2F6FA")]),
    ("TOPPADDING", (0, 0), (-1, -1), 6),
    ("BOTTOMPADDING", (0, 0), (-1, -1), 6),
]))
story.append(top)

story.append(PageBreak())

# Ejecucion y cierre
story.append(paragraph("10. Ejecucion y comportamiento general", styles["Section"]))
story.append(paragraph("Flujo general", styles["Subsection"]))
story.extend(bullets([
    "Inicia una instancia de DespachoCrud.",
    "Se solicitan las credenciales.",
    "Un rol valido abre el menu principal.",
    "Al cerrar sesion, la aplicacion vuelve al inicio de sesion.",
    "La aplicacion termina al agotar los intentos o elegir salir desde el menu.",
]))

story.append(paragraph("Cierre de sesion", styles["Subsection"]))
story.append(paragraph(
    "El usuario puede regresar a la pantalla de inicio desde la opcion Salir. "
    "El metodo devuelve el control al ciclo principal de Main sin destruir la coleccion "
    "de despachos creada durante la ejecucion.",
    styles["BodyText"],
))

story.append(paragraph("Propuestas futuras", styles["Subsection"]))
story.extend(bullets([
    "Agregar persistencia en archivos o base de datos.",
    "Implementar cifrado de contrasenas.",
    "Validar DNI, email y telefono.",
    "Agregar fechas personalizadas para entregas.",
    "Implementar registro de auditoria.",
    "Crear una interfaz grafica.",
]))

story.append(PageBreak())

# Conclusion
story.append(paragraph("11. Conclusion", styles["Section"]))
story.append(paragraph(
    "El sistema DocuGestion implementa un conjunto funcional consistente para autenticar "
    "usuarios, dividir permisos por roles y administrar despachos con sus remitos asociados. "
    "Las operaciones principales cubren alta, consulta, modificacion y baja tanto de usuarios "
    "como de despachos.",
    styles["BodyText"],
))
story.append(paragraph(
    "Desde el punto de vista tecnico, el proyecto integra herencia, abstraccion, "
    "polimorfismo, interfaces, encapsulamiento, composicion y colecciones. Desde el punto "
    "de vista funcional, ofrece diferentes niveles de acceso y mantiene reglas basicas de "
    "integridad para evitar remitos preparados sin control y eliminar el unico administrador.",
    styles["BodyText"],
))

doc = SimpleDocTemplate(
    str(OUTPUT_PDF),
    pagesize=A4,
    rightMargin=1.5 * cm,
    leftMargin=1.5 * cm,
    topMargin=1.6 * cm,
    bottomMargin=1.5 * cm,
    title="Informe de funcionalidades - DocuGestion",
    author="DocuGestion",
)

doc.build(story, onFirstPage=page_header_footer, onLaterPages=page_header_footer)
print(str(OUTPUT_PDF))
