// Selecciona el elemento canvas para la gráfica
const ctx = document.getElementById('lineChart').getContext('2d');

// Crea la gráfica de líneas
const lineChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio'], // Etiquetas de los datos
        datasets: [{
            label: 'Ridmo Cardiaco',
            data: [120, 190, 300, 500, 200, 300], // Datos de la gráfica
            borderColor: 'rgba(75, 192, 192, 1)', // Color de la línea
            backgroundColor: 'rgba(75, 192, 192, 0.2)', // Color del área bajo la línea
            borderWidth: 2,
            fill: true // Rellena el área bajo la línea
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true // Inicia el eje Y desde cero
            }
        }
    }
});
