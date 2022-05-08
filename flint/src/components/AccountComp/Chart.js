import React, { useEffect, useState } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import axios from 'axios';



class Chart extends React.Component{
  state = {
    isLoading: true,
    balances: [],
  };

  async componentDidMount() {
    const response = await fetch('api/balances/1');
    const body = await response.json();
    this.setState({ balances: body, isLoading: false });
  }
  

 render() {
  return (
      <div className="container">
      <LineChart
        width={800}
        height={400}
        data={this.state.balances}
        margin={{
          top: 5,
          right: 30,
          left: 20,
          bottom: 5,
        }}
      >
        <XAxis dataKey="timeStamp"
          tickCount={5}
        />
        <YAxis
        tickCount={10}/>
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="balances" stroke="#FFFF00" strokeWidth={2} activeDot={{ r: 8 }} />
        
      </LineChart>
      </div>
    );
 }
}

export default Chart;

