import {Component, ViewChild} from '@angular/core';
import {Chart, ChartConfiguration, ChartEvent, ChartType} from "chart.js";
import Annotation from "chartjs-plugin-annotation";
import {BaseChartDirective} from "ng2-charts";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent {



  private newLabel? = 'New label';

  constructor() {
    Chart.register(Annotation);
  }

  public lineChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: [65, 59, 80, 886, 56, 55, 40,40, 19, 86, 27, 90],
        label: 'Heures Travail',
        backgroundColor: 'rgba(148,159,177,0.2)',
        borderColor: 'rgba(148,159,177,1)',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)',
        fill: 'origin',
      },
      {
        data: [180, 480, 770, 90, 1600,28, 48, 40, 19, 86, 27, 90],
        label: 'Heures Travail',
        backgroundColor: 'rgba(77,83,96,0.2)',
        borderColor: 'rgba(77,83,96,1)',
        pointBackgroundColor: 'rgba(77,83,96,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(77,83,96,1)',
        fill: 'origin',
      },
      {
        data: [180, 480, 770, 90, 1700, 270, 400,300,400,500,12,25],
        label: 'Heures Suppplementaires',
        yAxisID: 'y1',
        backgroundColor: 'rgba(255,0,0,0.3)',
        borderColor: 'red',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)',
        fill: 'origin',
      },
    ],
    labels: ['Javaier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet','Aout','Septembre','Octobre','Novenre','Decembre'],
  };





  public lineChartOptions: ChartConfiguration['options'] = {

    elements: {
      line: {
        tension: 0.5,
      },
    },
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      y: {
        position: 'left',
      },
      y1: {
        position: 'right',
        grid: {
          color: 'rgba(255,0,0,0.3)',
        },
        ticks: {
          color: 'red',
        },
      },
    },

    plugins: {
      legend: { display: true },
      annotation: {
        annotations: [
          {
            type: 'line',
            scaleID: 'x',
            //value: 'March',
            borderColor: 'orange',
            borderWidth: 2,
            label: {
              display: true,
              position: 'center',
              color: 'orange',
              content: 'LineAnno',
              font: {
                weight: 'bold',
              },
            },
          },
        ],
      },
    },
  };

  public lineChartType: ChartType = 'line';

  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;

  ngAfterViewInit() {

    let date : Date = new Date();

    console.log("la date  : ");

  }

  // events
  public chartClicked({
                        event,
                        active,
                      }: {
    event?: ChartEvent;
    active?: object[];
  }): void {
    console.log(event, active);
  }

  public chartHovered({
                        event,
                        active,
                      }: {
    event?: ChartEvent;
    active?: object[];
  }): void {
    console.log(event, active);
  }


}
