//Terraform
//Creamos el grupo de recursos
resource "azurerm_resource_group" "gruporecursoterraform_luis" {
  name     = "terraformtest"
  location = "East US"
}


//Creamos el appserviceplan
resource "azurerm_app_service_plan" "app_serviceplans_luis" {
  name                = "terraform-appserviceplan"
  location            = azurerm_resource_group.gruporecursoterraform_luis.location
  resource_group_name = azurerm_resource_group.gruporecursoterraform_luis.name

  sku {
    tier = "Standard"
    size = "S3"
  }
}


//Creamos el appservices dentro del appserviceplan creado anteriormente
resource "azurerm_app_service" "app_service_luis" {
  name                = "terraform-appservice"
  location            = azurerm_resource_group.gruporecursoterraform_luis.location
  resource_group_name = azurerm_resource_group.gruporecursoterraform_luis.name
  app_service_plan_id = azurerm_app_service_plan.app_serviceplans_luis.id

  app_settings = {
    "Appsetting1" = "1",
	"Appsetting2" = "2"
  }

  connection_string {
    name  = "Database"
    type  = "SQLServerAzure"
    value = "Source=some-server.mydomain.com;Integrated Security=SSPI"
  }
}