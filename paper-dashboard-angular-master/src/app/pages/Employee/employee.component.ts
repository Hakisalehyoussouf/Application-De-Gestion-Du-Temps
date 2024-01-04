import { Component, OnInit } from '@angular/core';
import { User } from "../../models/user.model";
import { EmployeeService } from "../../services/employee.service";
import {ToastrService} from "ngx-toastr";
import {RapportService} from '../../services/rapport.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'employee-cmp',
  moduleId: module.id,
  templateUrl: 'employee.component.html'
})
export class EmployeeComponent implements OnInit {

  public employee: User = new User();
  public message: string = "";

  constructor(private employeeService: EmployeeService,
              private toastr: ToastrService,
              public rapportService: RapportService) {}


  public employees: User[] = [];
  public nomComplet:  string = ""
  public heuresTravail: number = 0;
  public heuresSupplementaires: number = 0;

  ngOnInit() {
    this.loadEmployees();
  }

  loadEmployees() {
    this.employeeService.getAllEmployees().subscribe(data => {
      this.employees = data;
    });
  }


  addEmployee() {
    console.log("Tentative d'ajout de l'employé...");

    if (!this.employee.nom || !this.employee.prenom || !this.employee.email || !this.employee.password) {
      this.toastr.error('Veuillez remplir tous les champs!', 'Erreur');
      return;
    }

    this.employeeService.addEmployee(this.employee).subscribe({

      next: (response) => {
        console.log('Réponse reçue:', response);
        // this.message = "Employé ajouté avec succès!";
        this.toastr.success('Employé ajouté avec succès!', '', {
          closeButton: true,
          timeOut: 3000,
          progressBar: true
        });
        this.employee = new User();
      },

      error: (err) => {
        console.error('Erreur lors de l’ajout de l’employé:', err);
        this.message = "Erreur lors de l'ajout de l'employé. Veuillez réessayer.";
        this.toastr.error(this.message, 'Erreur');
      }
    });
  }

  onSubmit() {
    this.addEmployee();
  }

  deleteEmployee(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer cet employé?')) {
      this.employeeService.deleteEmployee(id).subscribe({
        next: (response) => {
          this.toastr.success('Employé supprimé avec succès!', '', {
            closeButton: true,
            timeOut: 3000,
            progressBar: true
          });
          // Mettre à jour la liste des employés après la suppression
          this.loadEmployees();
        },
        error: (err) => {
          console.error('Erreur lors de la suppression de l’employé:', err);
          this.toastr.error('Erreur lors de la suppression de l’employé!', 'Erreur');
        }
      });
    }
  }


  GenererRapport(id: number, nom: string, prenom: string) {

    // this.rapportService.GenererRapportNomPrenom(id).subscribe({
    //   next : value => {
    //     console.log('dd' + value);
    //     console.log('dd' + value);
    //     console.log('dd' + value);
    //     console.log('dd' + value);
    //     console.log('dd' + value);
    //     console.log('dd' + value);
    //     this.nomComplet = value;
    //   },
    //   error : err => {
    //     console.log(err);
    //   }
    // });

    this.nomComplet = nom + " " + prenom;

    this.rapportService.GenererRapportHeuresTravail(id).subscribe({
      next : value => {
        this.heuresTravail = value;
      },
      error : err => {
        console.log(err);
      }
    });

    this.rapportService.GenererRapportHeuresSupplementaires(id).subscribe({
      next : value => {
          this.heuresSupplementaires = value;
      },
      error : err => {
        console.log(err);
      }
    });

  }
}
