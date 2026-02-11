const coreFeatures = [
  '코딩 테스트 필수 개념 정리',
  '문제 풀이 리뷰와 코드 리뷰 공유',
  '커뮤니티 기반 댓글/좋아요 소통'
];

function App() {
  return (
    <main className="container">
      <h1>everycodeacademy</h1>
      <p>코딩 테스트 학습과 코드 리뷰를 위한 커뮤니티 플랫폼 초기 화면입니다.</p>

      <section>
        <h2>핵심 기능(초기 스코프)</h2>
        <ul>
          {coreFeatures.map((feature) => (
            <li key={feature}>{feature}</li>
          ))}
        </ul>
      </section>

      <section className="api-status">
        <h2>백엔드 연동 경로</h2>
        <code>GET /api/health</code>
      </section>
    </main>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
