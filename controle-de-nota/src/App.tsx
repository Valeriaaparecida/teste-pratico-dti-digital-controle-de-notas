import { useEffect, useState } from "react";

function App() {

  const [alunos, setAlunos] = useState<any[]>([]);
  const [acimaMedia, setAcimaMedia] = useState<any[]>([]);
  const [frequenciaBaixa, setFrequenciaBaixa] = useState<any[]>([]);
  const [mediaTurma, setMediaTurma] = useState<number[]>([]);

  const [nome, setNome] = useState("");
  const [frequencia, setFrequencia] = useState(0);
  const [notas, setNotas] = useState<number[]>([0,0,0,0,0]);

  useEffect(() => {
    carregarDados();
  }, []);

  function carregarDados() {
    fetch("http://localhost:8080/aluno")
      .then(res => res.json())
      .then(data => setAlunos(data));

    fetch("http://localhost:8080/aluno/media-turma")
      .then(res => res.json())
      .then(data => setMediaTurma(data));
  }

  function buscarAcimaMedia() {
    fetch("http://localhost:8080/aluno/acima-media")
      .then(res => res.json())
      .then(data => setAcimaMedia(data));
  }

  function buscarFrequenciaBaixa() {
    fetch("http://localhost:8080/aluno/frequencia-baixa")
      .then(res => res.json())
      .then(data => setFrequenciaBaixa(data));
  }

  function salvarAluno() {
    fetch("http://localhost:8080/aluno", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ nome, frequencia, notas })
    }).then(() => {
      carregarDados();
      setNome("");
      setNotas([0,0,0,0,0]);
      setFrequencia(0);
    });
  }

  function alterarNota(i:number, valor:number) {
    const copia = [...notas];
    copia[i] = valor;
    setNotas(copia);
  }

  return (
    <div style={{ padding: 40 }}>

      <h1>Gestão Acadêmica</h1>

      <h2>Novo aluno</h2>

      <input placeholder="Nome" value={nome} onChange={e=>setNome(e.target.value)} />

      <br /><br />

      {notas.map((_, i) => (
  <input
    key={i}
    type="number"
    min={0}
    max={10}
    step="0.1"
    placeholder={`Nota ${i + 1}`}
    onChange={e => alterarNota(i, Number(e.target.value))}
  />
))}


      <br /><br />

      <input
  type="number"
  min={0}
  max={100}
  step="1"
  placeholder="Frequência"
  value={frequencia}
  onChange={e => setFrequencia(Number(e.target.value))}
/>


      <br /><br />

      <button onClick={salvarAluno}>Salvar</button>

      <hr />

      <button onClick={buscarAcimaMedia}>Alunos acima da média</button>
      <button onClick={buscarFrequenciaBaixa}>Frequência baixa</button>

      <h2>Média da turma por disciplina</h2>

      {mediaTurma.map((m,i)=>(
        <p key={i}>Disciplina {i+1}: {m.toFixed(2)}</p>
      ))}

      <h2>Todos alunos</h2>
      {alunos.map(a=>(
        <div key={a.id}>
          {a.nome} — média {a.media} — frequência {a.frequencia}%
        </div>
      ))}

      <h2>Acima da média</h2>
      {acimaMedia.map(a=>(
        <div key={a.id}>{a.nome}</div>
      ))}

      <h2>Frequência baixa</h2>
      {frequenciaBaixa.map(a=>(
        <div key={a.id}>{a.nome}</div>
      ))}

    </div>
  );
}

export default App;
