import React, { useEffect, useState } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import axios from 'axios';



const Chart = () => {
  const [data, setData] = useState('');

  useEffect(() => {
    getAllBalances();
  }, []);
  
  const getAllBalances = () => {
    axios.get('api/balances/1')
      .then((response) => {
        const allData = response.data.allData;
        setData(allData);
        console.log(data);
      })
      .catch((err) => console.error(`Error: ${err}`));
  }
  


  
    return (
      <LineChart
        width={500}
        height={300}
        data={data}
        margin={{
          top: 5,
          right: 30,
          left: 20,
          bottom: 5,
        }}
      >
        <XAxis dataKey="name" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="pv" stroke="#FFFF00" strokeWidth={2} activeDot={{ r: 8 }} />
        <Line type="monotone" dataKey="uv" stroke="#800080" strokeWidth={2} />
      </LineChart>
    );
  }

export default Chart;
